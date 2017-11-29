package com.stackd.stackd.activities;

import android.Manifest;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;
import com.stackd.stackd.R;
import com.stackd.stackd.adapters.ResumeImageAdapter;
import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Tag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.LinkedHashMap;



public class StackActivity extends AppCompatActivity {
    public static final String RESUME_ID_KEY = "resumeId";
    public static final long RESUME_ID_NEW = -1;
    private static final int REQUEST_WRITE_STORAGE = 112;
    private static final int REQUEST_CAM = 0;
    private ResumeImageAdapter adapter;
    int REQUEST_CODE = 99;
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
                else {
                    i.putExtra(EditActivity.IMAGE_R_KEY, adapter.getDummyResourceId(position));
                }
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
            // Write to csv file
            System.out.print("\n \n Print CSV \n \n ");

            // dummy alert box, should export all resumes to cvs file
            AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
            alertBox.setMessage("Are you sure you want to export resumes?");
            alertBox.setTitle("Export");
            alertBox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //writeToCsv();

                    boolean hasPermission = (ContextCompat.checkSelfPermission(StackActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                    if (!hasPermission) {
                        ActivityCompat.requestPermissions(StackActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_WRITE_STORAGE);
                    }

                    if (isExternalStorageWritable()){
                        File dir = getAlbumStorageDir(getApplicationContext(),"test" );
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
     *  Called when the camera button is clicked. Should open a camera activity.
     * @param v the button that was clicked.
     */
    public void onCameraBtnClick(View v){
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
       // ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODEE);

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

    /* Called when we want to export the resumes to a CSV file
     *
     */
    private void writeToCsv() {
        File file = new File(getApplicationContext().getFilesDir(), "export_test.csv");
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
        sb.append("Name");
        sb.append(cvsSplitBy);
        sb.append("Date");
        sb.append(cvsSplitBy);
        sb.append("URL");
        sb.append(cvsSplitBy);
        sb.append("Tags");
        sb.append(rowSplitBy);
        List<Resume> resumes = adapter.getFilteredResumes();
        for (Resume r : resumes) {
            sb.append(r.getCandidateName());
            sb.append(cvsSplitBy);
            sb.append(r.getCollectionDate());
            sb.append(cvsSplitBy);
            sb.append(r.getUrl());
            sb.append(cvsSplitBy);
            sb.append(r.getTagList());
            sb.append(rowSplitBy);
        }

        pw.write(sb.toString());
        pw.close();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while ((line = br.readLine()) != null) {
                String[] rList = line.split(cvsSplitBy);
                for (String s : rList) {
                    System.out.print(s + "\t\t");
                }
                System.out.print("\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
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


    public File getAlbumStorageDir(Context context, String fileName) {
        // Get the directory for the app's private pictures directory.
        // Find the SD Card path

        // Create a new folder in SD Card
        File dir = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), fileName);

        // Find the SD Card path
        /*File filepath = Environment.getExternalStorageDirectory();
*/
        // Create a new folder in SD Card
        /*File dir = new File(filepath.getAbsolutePath()
                + "test");*/

       // File dir = new File ("/storage/emulated/0/Documents/test");

        if (!dir.mkdirs() && !dir.exists()) {
            // Show a toast message on successful save
            System.out.println("Failed to create dir");
            Toast.makeText(StackActivity.this,"FAILED DIR",
                    Toast.LENGTH_SHORT).show();
            System.out.println("DIR: FAILED" + dir.getAbsolutePath());
            return null;

        }
        System.out.println("DIR: SUCCESS" + dir.getAbsolutePath());
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
                    Toast.makeText(StackActivity.this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    public void writeResume(File dir) {
        // Get resume as well
        //File file = new File( getApplicationContext().getExternalFilesDir(
                //Environment.DIRECTORY_DOWNLOADS) ,"testResume");
        File file = new File( dir ,"testResume");

        if (dir.exists()) {
            Toast.makeText(StackActivity.this, "Directory Exists", Toast.LENGTH_SHORT).show();
        }
        for (File f : dir.listFiles()) {
            System.out.println(f.getAbsolutePath());
        }



       // File file = new File("/storage/emulated/0/Documents/test/testFile");
        Bitmap bitmap;
        OutputStream output;

        // Retrieve the image from the res folder
        bitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.r1)).getBitmap();


        try {
            output = new FileOutputStream(file);
            // Compress into png format image from 0% - 100%
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            Log.e("ERROR", "Could not create file");
            // Show a toast message on successful save
            Toast.makeText(StackActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show a toast message on successful save
        //Toast.makeText(StackActivity.this, file.getAbsolutePath()   ,
          //     Toast.LENGTH_SHORT).show();

        // Try reading the image:
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String line;
            System.out.println("\n\n\n\n\nIMAGE");
            while ((line = br.readLine()) != null) {

                //System.out.print(line + "\n");


            }
        } catch (IOException e) {
            Log.e("ERROR", "Can't read file");
        }






    }
}

