package com.stackd.stackd.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.stackd.stackd.R;
import com.stackd.stackd.db.App;
import com.stackd.stackd.model.Resume;
import com.stackd.stackd.model.Tag;


import java.io.File;
import java.lang.reflect.Array;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    LinearLayout tagListLayout;
    ArrayList<CheckBox> checkBoxes;
    Resume resume;
    App app = App.getApp(App.company.getId(), App.recruiter.getId());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Fields needed from other screens
        final String resume_url = "";
        final String candidate_name = "";

        File imgFile = new  File(resume_url);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView resume_img = (ImageView) findViewById(R.id.current_resumeJPG);

            resume_img.setImageBitmap(myBitmap);

        }


        // Initialize the resume
        resume = new Resume(App.recruiter.getId());// TODO: retrrive id  from other screen

        // Add checkboxes dynamically
        tagListLayout = (LinearLayout) findViewById(R.id.tagListLayout);
        // Retrieve the company's list of tags TODO: call getTags from DOA, SCROLL

        // The company's tags
        final List<Tag> tagList = app.getCompanyTags();

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


        // The DONE button which saves the resume and adds it to the database
        final Button submitButton = (Button) findViewById(R.id.submit_resume);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Insert the resume
                resume.setTagList(tagList);
                resume.setUrl(resume_url);
                resume.setRecruiterComments(comment_field.getText().toString());
                resume.setCollectionDate(new SimpleDateFormat("DD-MM-YYYY"));
                resume.setCandidateName(candidate_name);
            }
        });
    }


}
