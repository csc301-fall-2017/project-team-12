package com.stackd.stackd.activities;

import android.Manifest;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;
import com.stackd.stackd.R;
import com.stackd.stackd.adapters.ResumeImageAdapter;
import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Tag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StackActivity extends AppCompatActivity {
    public static final String RESUME_ID_KEY = "resumeId";
    public static final String RESUME_ID_NEW = "-1";
    private ResumeImageAdapter adapter;
    private int REQUEST_CODE = 99;
    private static final int REQUEST_WRITE_STORAGE = 112;
    private LinkedHashMap<String, Boolean> activeTags = new LinkedHashMap<>();
    private int activeRatingConstraint = -1; // no rating constraint selected

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // initialize adapter and assign it to a resume grid
        GridView gridview = (GridView) findViewById(R.id.gridview);
        adapter = new ResumeImageAdapter(this);
        gridview.setAdapter(adapter);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // dummy result, should open the review activity for the given resume
                Intent i = new Intent(StackActivity.this, EditActivity.class);
                String imgPath = adapter.getImgPath(position);
                i.putExtra(EditActivity.IMAGE_PATH_KEY, imgPath);
                String resumeId = ((Resume) adapter.getItem(position)).getId();
                i.putExtra(RESUME_ID_KEY, resumeId);
                startActivity(i);
            }
        });

        populateTagsList();
        // for each tag in a tag list, add a button to the tag bar.
        LinearLayout tagsList = (LinearLayout) findViewById(R.id.tag_list);
        for (String strTag : activeTags.keySet()) {
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
                    } else {
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
                // reset all filters, if an empty query is passed
                if (query.equals("")) {
                    adapter.getFilter().filter(null);
                } else {
                    adapter.getFilter().filter(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
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

                    boolean hasPermission = (ContextCompat.checkSelfPermission(StackActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                    if (!hasPermission) {
                        ActivityCompat.requestPermissions(StackActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_WRITE_STORAGE);
                    }

                    if (isExternalStorageWritable()){
                        File dir = getAlbumStorageDir("Stackd_" + new SimpleDateFormat("yyyy_MM_dd_" +
                                "hh_mm").format(new Date()) );
                        writeResume(dir);
                    }

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
     * Called when the camera button is clicked. Should open a camera activity.
     *
     * @param v the button that was clicked.
     */
    public void onCameraBtnClick(View v) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            int preference = ScanConstants.OPEN_CAMERA;
            Intent intent = new Intent(this, ScanActivity.class);

            intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
            startActivityForResult(intent, REQUEST_CODE);
        }
        else {
            // request camera permissions
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
        }
    }

    /**
     * Called when camera finishes scanning. Should launch edit activity with scanned fragment.
     *
     * @param requestCode correct permission code.
     * @param resultCode  ensures correct activity result.
     * @param data        instance of scanning intent after taking a picture.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
            // transform uri to file
            String imgPath = getRealPathFromUri(this, uri);
            Intent i = new Intent(StackActivity.this, EditActivity.class);
            i.putExtra(EditActivity.IMAGE_PATH_KEY, imgPath);
            i.putExtra(RESUME_ID_KEY, RESUME_ID_NEW);
            startActivity(i);
        }
    }


    private void populateTagsList() {
        for (Tag tag : adapter.getTags()) {
            activeTags.put(tag.getName(), false);
        }
    }

    /**
     * Filters resumes by rating, showing only resumes with a selected rating.
     * For example, pressing "yes" will only show resumes with rating 2.
     * Pressing the same button again will unset the filter.
     * @param v button pressed
     */
    public void filterByRating(View v) {
        Button yesBtn = (Button) findViewById(R.id.yes_button);
        Button maybeBtn = (Button) findViewById(R.id.maybe_button);
        Button noBtn = (Button) findViewById(R.id.no_button);
        Button activeBtn = null;
        Button inactiveBtn1 = null, inactiveBtn2 = null;
        if (yesBtn == v) {
            if (activeRatingConstraint != 2) {
                activeRatingConstraint = 2;
                activeBtn = yesBtn;
                inactiveBtn1 = noBtn;
                inactiveBtn2 = maybeBtn;
            } else {
                activeRatingConstraint = -1;
            }

        } else if (noBtn == v) {
            if (activeRatingConstraint != 0) {
                activeRatingConstraint = 0;
                activeBtn = noBtn;
                inactiveBtn1 = yesBtn;
                inactiveBtn2 = maybeBtn;
            } else {
                activeRatingConstraint = -1;
            }

        } else if (maybeBtn == v) {
            if (activeRatingConstraint != 1) {
                activeRatingConstraint = 1;
                activeBtn = maybeBtn;
                inactiveBtn1 = noBtn;
                inactiveBtn2 = yesBtn;
            } else {
                activeRatingConstraint = -1;
            }
        }
        if(activeRatingConstraint != -1) {
            int activeColor =
                    ContextCompat.getColor(getApplicationContext(), R.color.colorWhite);
            int inactiveColor =
                    ContextCompat.getColor(getApplicationContext(), R.color.colorGrey);
            int textColor = ContextCompat.getColor(getApplicationContext(), R.color.colorBlack);
            activeBtn.getBackground().setColorFilter(activeColor, PorterDuff.Mode.MULTIPLY);
            activeBtn.setTextColor(textColor);
            inactiveBtn1.getBackground().setColorFilter(inactiveColor, PorterDuff.Mode.MULTIPLY);
            inactiveBtn2.getBackground().setColorFilter(inactiveColor, PorterDuff.Mode.MULTIPLY);
        }
        else {
            int standardColor =
                    ContextCompat.getColor(getApplicationContext(), R.color.colorGrey);
            int textColor = ContextCompat.getColor(getApplicationContext(), R.color.colorWhite);
            yesBtn.getBackground().setColorFilter(standardColor, PorterDuff.Mode.MULTIPLY);
            maybeBtn.getBackground().setColorFilter(standardColor, PorterDuff.Mode.MULTIPLY);
            noBtn.getBackground().setColorFilter(standardColor, PorterDuff.Mode.MULTIPLY);
            yesBtn.setTextColor(textColor);
            maybeBtn.setTextColor(textColor);
            noBtn.setTextColor(textColor);
        }
        adapter.setRatingConstraint(activeRatingConstraint);
        adapter.getFilter().filter(null);
    }

    /**
     * Extracts a real path from uri. Needed for getting a real path from uri returned by ScanActivity
     * @param context current context
     * @param contentUri uri from which path should be extracted
     * @return string path
     */
    private String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* Checks if external storage is available for read and write
     * Sourced from Android Dev
     * */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


    public File getAlbumStorageDir(String fileName) {
        // Get the directory for the app's private pictures directory.
        // Find the SD Card path

        // Create a new folder in SD Card
        File dir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), fileName);

        if (!dir.mkdirs() && !dir.exists()) {
            // Show a toast message on successful save
            Toast.makeText(StackActivity.this,"FAILED DIR",
                    Toast.LENGTH_SHORT).show();
            return null;

        }
        return dir;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    // pass
                } else
                {
                    Toast.makeText(StackActivity.this, "The app was not allowed to" +
                            " write to your storage. Hence, it cannot function properly. Please" +
                            " consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void writeResume(File dir) {

        List<Resume> resumeExport = adapter.getFilteredResumes();
        Map<Resume, File> resumeToFile = adapter.get_resumesToFiles();

        for (Resume r : resumeExport) {
            // Write resume to file
            try {
                File newFile = new File(dir, resumeToFile.get(r).getName());
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }

                FileOutputStream outputStream = new FileOutputStream (newFile);
                FileInputStream inputStream = new FileInputStream(resumeToFile.get(r));

                byte[] buffer    =   new byte[10*1024];

                for (int length; (length = inputStream.read(buffer)) != -1; ){
                    outputStream.write(buffer, 0, length);
                }

            } catch (Exception e) {
                Log.e("ERROR", "Could not create file");
                // Show a toast message on successful save
                Toast.makeText(StackActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                return;
            }

        }

        writeToCsv(dir);

        // Show a toast message on successful save
        Toast.makeText(StackActivity.this, "Resumes exported successfully"   ,
                Toast.LENGTH_SHORT).show();

    }

    /* Called when we want to export the resumes to a CSV file
    *
    */
    private void writeToCsv(File dir) {
        File file = new File(dir, "report.csv");
        try {
                if (!file.exists()) {
                    file.createNewFile();
                }

        } catch (Exception e) {
            e.printStackTrace();
            return;

        }
        PrintWriter pw = null;

        String line = "";
        String cvsSplitBy = ",";
        String rowSplitBy = "\n";

        try {
            pw = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        StringBuilder sb = new StringBuilder();
        sb.append("Email");
        sb.append(cvsSplitBy);
        sb.append("Date");
        sb.append(cvsSplitBy);
        sb.append("Tags");
        sb.append(cvsSplitBy);
        sb.append("Ratings");
        sb.append(cvsSplitBy);
        sb.append("Comments");
        sb.append(rowSplitBy);
        List<Resume> resumes = adapter.getFilteredResumes();
        for (Resume r : resumes) {
            sb.append(r.toString());
        }

        pw.write(sb.toString());
        pw.close();


    }
}

