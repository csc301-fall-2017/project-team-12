package com.stackd.stackd.activities;

import android.Manifest;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;
import com.stackd.stackd.R;
import com.stackd.stackd.adapters.ResumeImageAdapter;
import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Tag;

import java.io.IOException;
import java.util.LinkedHashMap;


public class StackActivity extends AppCompatActivity {
    public static final String RESUME_ID_KEY = "resumeId";
    public static final long RESUME_ID_NEW = -1;
    private static final int REQUEST_CAM = 0;
    private ResumeImageAdapter adapter;
    private int REQUEST_CODE = 99;
    private LinkedHashMap<String, Boolean> activeTags = new LinkedHashMap<>();
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // initialize the grid view and its adapter
        GridView gridview = (GridView) findViewById(R.id.gridview);
        adapter = new ResumeImageAdapter(this);
        gridview.setAdapter(adapter);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // dummy result, should open the review activity for the given resume
                Intent i = new Intent(StackActivity.this, EditActivity.class);
                String imgURL = adapter.getImageURL(position);
                if(imgURL != null && imgURL.length() > 0)
                    i.putExtra(EditActivity.IMAGE_URI_KEY, imgURL);
                long resumeId = ((Resume) adapter.getItem(position)).getId();
                i.putExtra(RESUME_ID_KEY, resumeId);
                startActivity(i);
            }
        });

        populateTagsList();
        // for each tag in a tag list, add a button to the tag bar.
        LinearLayout tagsList = (LinearLayout)findViewById(R.id.tag_list);
        for(String strTag : activeTags.keySet()) {
            final Button btn = new Button(getApplicationContext());
            int backgroundColor =
                    ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);
            int textColor = ContextCompat.getColor(getApplicationContext(), R.color.colorWhite);
            btn.getBackground().setColorFilter(backgroundColor, PorterDuff.Mode.MULTIPLY);
            btn.setTextColor(textColor);
            btn.setText(strTag);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (activeTags.get(btn.getText().toString())) {
                        // filter resumes based on the tag selected
                        int backgroundColor =
                                ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorAccent);
                        btn.getBackground().setColorFilter(backgroundColor,
                                PorterDuff.Mode.MULTIPLY);
                        activeTags.put(btn.getText().toString(), false);
                        adapter.removeConstraint(btn.getText().toString());
                        adapter.getFilter().filter(null);
                    }
                    else {
                        // unset the tag, show resumes even without this tag
                        int backgroundColor =
                                ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimary);
                        btn.getBackground().setColorFilter(backgroundColor,
                                PorterDuff.Mode.MULTIPLY);
                        activeTags.put(btn.getText().toString(), true);
                        adapter.addConstraint(btn.getText().toString());
                        adapter.getFilter().filter(null);
                    }
                }
            });
            tagsList.addView(btn);
        }
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.stack_menu, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        // reset all filters, once search view is closed
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adapter.getFilter().filter(null);
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.equals("")){
                    adapter.getFilter().filter(null);
                }
                else{
                    adapter.getFilter().filter(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals("")) {
                    onQueryTextSubmit("");
                }
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            // dummy alert box, should export all resumes to cvs file
            AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
            alertBox.setMessage("Are you sure you want to export resumes?");
            alertBox.setTitle("Export");
            alertBox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertBox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertBox.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            // use the query to search resume list for a resumes matching the query
            Toast.makeText(StackActivity.this, "Searching for " + query,
                    Toast.LENGTH_SHORT).show();
            adapter.getFilter().filter(query);
        }
    }

    /**
     *  Called when the camera button is clicked. Should open a camera activity.
     * @param v the button that was clicked.
     */
    public void onCameraBtnClick(View v){
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED){
            int preference = ScanConstants.OPEN_CAMERA;
            Intent intent = new Intent(this, ScanActivity.class);

            intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            // TODO handle case where permissions are denied
        }

    }

    /**
     *  Called when camera finishes scanning. Should launch edit activity with scanned fragment.
     * @param requestCode correct permission code.
     * @param resultCode ensures correct activity result.
     * @param data instance of scanning intent after taking a picture.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                Intent i = new Intent(StackActivity.this, EditActivity.class);
                i.putExtra(EditActivity.IMAGE_URI_KEY, uri.toString());
                i.putExtra(RESUME_ID_KEY, RESUME_ID_NEW);
                startActivity(i);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void populateTagsList() {
        for(Tag tag : adapter.getTags()) {
            activeTags.put(tag.getName(), false);
        }
    }
}

