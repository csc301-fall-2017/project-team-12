package com.stackd.stackd.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
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

import com.stackd.stackd.R;
import com.stackd.stackd.adapters.ResumeImageAdapter;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class StackActivity extends AppCompatActivity {
    private ResumeImageAdapter adapter;
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
            Button btn = new Button(getApplicationContext());
            int backgroundColor = ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);
            btn.getBackground().setColorFilter(backgroundColor, PorterDuff.Mode.MULTIPLY);
            btn.setText(tags.next());
            tagsList.addView(btn);
        }

        handleIntent(getIntent());
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
        // dummy alert
        AlertDialog.Builder alertBox = new AlertDialog.Builder(this).
                setMessage("Opening camera activity").
                setTitle("Open camera");
        alertBox.show();
    }

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

