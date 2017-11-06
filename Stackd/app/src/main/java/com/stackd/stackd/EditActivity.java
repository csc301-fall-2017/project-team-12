package com.stackd.stackd;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import com.stackd.stackd.helpers.Resume;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    LinearLayout tagListLayout;
    ArrayList<CheckBox> checkBoxes;
    Resume resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Initialize the resume
        resume = new Resume();
        resume.setDate( new SimpleDateFormat("DD-MM-YYYY"));

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
            tagListLayout.addView(cb);
        }

    }


}
