package com.stackd.stackd.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;
import com.stackd.stackd.R;
import com.stackd.stackd.adapters.ResumeImageAdapter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_CONTACTS;


public class StackActivity extends AppCompatActivity {
    private static final int REQUEST_CAM = 0;
    private ResumeImageAdapter adapter;
    private ImageView scannedImageView;
    private LinkedHashMap<String, Boolean> activeTags = new LinkedHashMap<String, Boolean>();
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
                startActivity(i);
            }
        });

        populateTagsList();
        LinearLayout tagsList = (LinearLayout)findViewById(R.id.tag_list);
        Iterator<String> tags = activeTags.keySet().iterator();
        while (tags.hasNext()) {
            final Button btn = new Button(getApplicationContext());
            int backgroundColor =
                    ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);
            btn.getBackground().setColorFilter(backgroundColor, PorterDuff.Mode.MULTIPLY);
            btn.setText(tags.next());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (activeTags.get(btn.getText().toString())) {
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

//        handleIntent(getIntent());
//        int REQUEST_CODE = 99;
//        int preference = ScanConstants.OPEN_CAMERA;
//        Intent intent = new Intent(this, ScanActivity.class);
//        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
//        startActivityForResult(intent, REQUEST_CODE);
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
    int REQUEST_CODE = 99;
    private static final String TAG = "MyActivity";
    private static final String TAAG = "IMHERE";
    /**
     *  Called when the camera button is clicked. Should open a camera activity.
     * @param v the button that was clicked.
     */
    public void onCameraBtnClick(View v){
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, REQUEST_CODE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED){
            int preference = ScanConstants.OPEN_CAMERA;
            Intent intent = new Intent(this, ScanActivity.class);

            intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
            startActivityForResult(intent, REQUEST_CODE);
            Log.e(TAAG, "onCameraBtnClick: " );
        } else {

            Log.e(TAG, "onCameraBtnClick: " );
        }

    }
    private static final String TAAAG = "ONACTIVITYRESULT";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAAAG, "onActivityResult " );
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                getContentResolver().delete(uri, null, null);
                scannedImageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAM) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if(mayRequestContacts()){

                }
            }
        }
    }


    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_CAM);
        return false;
    }
//


    private void populateTagsList() {
        activeTags.put("PEY", false);
        activeTags.put("Internship", false);
        activeTags.put("Full-time", false);
        activeTags.put("C++", false);
        activeTags.put("C#", false);
        activeTags.put("C", false);
        activeTags.put("CSS", false);
        activeTags.put("HTML", false);
        activeTags.put("Java", false);
        activeTags.put("JavaScript", false);
        activeTags.put("Python", false);
        activeTags.put("R", false);
        activeTags.put("Swift", false);

    }
}

