package com.stackd.stackd.activities;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.stackd.stackd.R;
import com.stackd.stackd.db.DataManager;
import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Tag;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    LinearLayout tagListLayout;
    ArrayList<CheckBox> checkBoxes;
    Resume resume;
    // Dummy Values
    long cId = 1;
    long rId = 21;
    DataManager dataManager = DataManager.getDataManager(cId, rId);
    AlertDialog alertBox = null;
    RelativeLayout drawLayout;


    private ImageView ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setUpAlertBox(this);

        // Fields needed from other screens
        Bundle resumeUri = getIntent().getExtras();
        Uri uri;
        ImageView resumeView = (ImageView)findViewById(R.id.current_resume);
        if(resumeUri != null) {
            uri = Uri.parse(getIntent().getExtras().getString("imageUri"));
            resumeView.setImageURI(uri);
        }
        else {
            uri = Uri.parse("R.drawable.resume_template");
            resumeView.setImageResource(R.drawable.resume_template);
        }
        drawLayout = (RelativeLayout) this.findViewById(R.id.drawLayout);
        drawLayout.setVisibility(RelativeLayout.GONE);

        final String resume_url = uri.toString();
        final String candidate_name = "Candidate 1";

        // Add checkboxes dynamically
        tagListLayout = (LinearLayout) findViewById(R.id.tagListLayout);

        // The company's tags
        final List<Tag> tagList = dataManager.getCompanyTags();

        // The tags the resume contains
        final List<Tag> resumeTags = new ArrayList<>();

        for (final Tag tag: tagList) {
            final CheckBox cb = new CheckBox(this);
            cb.setId(tagList.indexOf(tag));
            cb.setText(tag.getName());
            cb.setTextColor(Color.WHITE);
            cb.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    resumeTags.add(tag);
                }
            });
            tagListLayout.addView(cb);
        }

        final EditText comment_field = (EditText) findViewById(R.id.comment_field);

        // The DONE button, opens the rating dialog box and builds the resume
        final Button submitButton = (Button) findViewById(R.id.submit_resume);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Initialize the resume
                resume = new Resume.Builder()
                        .id(1)
                        .rid(dataManager.getRecruiter().getRecId())
                        .tagList(resumeTags)
                        .url(resume_url)
                        .recruiterComments(comment_field.getText().toString())
                        .collectionDate(new SimpleDateFormat("DD-MM-yyyy").format(new Date()))
                        .candidateName(candidate_name).build();

                alertBox.show();
            }
        });

        FloatingActionButton highlightButton = (FloatingActionButton) findViewById(R.id.highlightButton);
        highlightButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawLayout.getVisibility() == RelativeLayout.GONE) {
                    drawLayout.setVisibility(RelativeLayout.VISIBLE);
                } else {
                    drawLayout.setVisibility(RelativeLayout.GONE);
                }
            }

        });
    }

    /*
     * Find the URI from the given resID
     */
    public static Uri resIdToUri(Context context, int resId) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                context.getResources().getResourcePackageName(resId) + '/' +
                context.getResources().getResourceTypeName(resId) + '/' +
                context.getResources().getResourceEntryName(resId) );

    }

    public void setUpAlertBox(Context context) {

        // The button icons
        //ImageView no = new ImageView(this);
        //no.setImageResource(R.drawable.splattered);

        // setup the alert builder
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Candidate Status");
        //alertBuilder.setView(no);
        // add a list
        String[] ratings = {"Yes", "No", "Maybe"};

        alertBuilder.setItems(ratings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: resume.setRating(2);// yes
                    case 1: resume.setRating(0);// no
                    case 2: resume.setRating(1);// maybe

                }
                // Insert the resume into the database
                assert(resume != null);
                dataManager.insertResume(resume);
                // Review it and add a rating
                dataManager.addReview(resume.getId(), resume.getCollectionDate(), resume.getRating());

                // Go back to stack view
                Intent i = new Intent(EditActivity.this, StackActivity.class);
                startActivity(i);
            }
        });
        alertBox = alertBuilder.create();
    }

}

