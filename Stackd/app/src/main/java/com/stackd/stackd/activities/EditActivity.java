package com.stackd.stackd.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.scanlibrary.ScanConstants;
import com.stackd.stackd.R;
import com.stackd.stackd.helpers.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    LinearLayout tagListLayout;
    ArrayList<CheckBox> checkBoxes;
    Resume resume;


    private ImageView ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Initialize the resume
        resume = new Resume();
        //resume.setDate( new SimpleDateFormat("DD-MM-YYYY"));

        // Add checkboxes dynamically
        tagListLayout = (LinearLayout) findViewById(R.id.tagListLayout);

        // Retrieve the company's list of tags TODO: call getTags from DOA, SCROLL
        ArrayList<String> tagList =  new ArrayList<>();
        tagList.add("PEY");
        tagList.add("Intern");
        tagList.add("Java");

        for (final String tag: tagList) {
            final CheckBox cb = new CheckBox(this);
            cb.setId(tagList.indexOf(tag));
            cb.setText(tag);
            cb.setTextColor(Color.WHITE);
            cb.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    resume.addTags((String) cb.getText());
                }
            });
            tagListLayout.addView(cb, 0);
        }

        // Fields needed from other screens
        Uri myUri = Uri.parse(getIntent().getExtras().getString("imageUri"));

        Uri uri=Uri.parse("R.drawable.resume_template");
        int resId = R.drawable.resume_template;

        ImageView resumeView = (ImageView)findViewById(R.id.current_resume);
        resumeView.setImageURI(myUri);

        final String resume_url = uri.toString();
        final String candidate_name = "";

        File imgFile = new  File(resume_url);

        if(imgFile.exists()){
            System.out.print("Image exists\n \n \n ");
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView resume_img = (ImageView) findViewById(R.id.current_resume);

            resume_img.setImageBitmap(myBitmap);

        }

    }


}
