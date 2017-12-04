package com.stackd.stackd.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.stackd.stackd.R;
import com.stackd.stackd.db.DataManager;
import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Tag;
import com.stackd.stackd.helpers.Consumer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditActivity extends AppCompatActivity {
    public static final String IMAGE_PATH_KEY = "imagePath";
    private LinearLayout tagListLayout;
    private Resume resume;
    private DataManager dataManager;
    private AlertDialog alertBox = null;
    private AlertDialog setCandidateEmailAlertBox = null;
    private List<Tag> resumeTags;
    private Map<Button, Tag> tagButtonMap = new HashMap<>();

    private ImageView resumeView;
    private ImageView resumeViewShadow;
    private String resumeImgPath;
    File imgFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setUpAlertBox(this);
        setUpCandidateEmail(this);

        dataManager = DataManager.getDataManager(DataManager.cId, DataManager.rId, getApplicationContext());
        // Fields needed from other screens
        Bundle extras = getIntent().getExtras();
        resumeImgPath = extras.getString(IMAGE_PATH_KEY);
        // set picture to the picture of the current resume
        resumeView = (ImageView)findViewById(R.id.current_resume);
        resumeViewShadow = (ImageView)findViewById(R.id.current_resume_shadow);

        // if for some reason, image does not exist, just display comments and tags
        if(resumeImgPath != null) {
            imgFile = new File(resumeImgPath);
            resumeView.setImageURI(Uri.fromFile(imgFile));
            resumeViewShadow.setImageURI(Uri.fromFile(imgFile));
            Bitmap bitmap = load();
            resumeView.setImageBitmap(bitmap);
        }

        String resumeId = extras.getString(StackActivity.RESUME_ID_KEY);
        if(resumeId.equals(StackActivity.RESUME_ID_NEW)) {
            // Initialize a new resume
            // NOTE: as of right now, we are not using url field at all
            resume = new Resume.Builder()
                    .rid(DataManager.rId)
                    .collectionDate(new SimpleDateFormat("DD-MM-yyyy").format(new Date()))
                    .tagList(new ArrayList<Tag>())
                    .build();
            resumeTags = new ArrayList<>();
            setCandidateEmailAlertBox.show();
        }
        else {
            // make a resume immutable, since it has already been reviewed
            Button btnDone = (Button) findViewById(R.id.submit_resume);
            btnDone.setEnabled(false);
            btnDone.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            FloatingActionButton hlButton = (FloatingActionButton) findViewById(R.id.highlightButton);
            hlButton.setEnabled(false);
            hlButton.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            EditText editText = ((EditText)findViewById(R.id.comment_field));
            editText.setTextColor(getResources().getColor(R.color.colorPrimary));
            editText.setEnabled(false);
            resumeView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
            // load an existing resume
            for(Resume r : dataManager.getCompany().getResumes())
                if(r.getId().equals(resumeId)) {
                    resume = r;
                    editText.setText(resume.getRecruiterComments());
                    if(resume.getTagList() != null)
                        resumeTags = resume.getTagList();
                    else
                        resumeTags = new ArrayList<>();
                    break;
                }
        }


        // Add checkboxes dynamically
        tagListLayout = (LinearLayout) findViewById(R.id.tagListLayout);

        LinearLayout tagsList = (LinearLayout)findViewById(R.id.tagListLayout);
        List<Tag> tags = dataManager.getCompanyTags();
        for(Tag tag : tags) {
            String tagName = tag.getName();
            final Button btn = new Button(getApplicationContext());
            tagButtonMap.put(btn, tag);
            if(!resumeId.equals(StackActivity.RESUME_ID_NEW)) {
                btn.setEnabled(false);
            }
            int backgroundColor =
                    ContextCompat.getColor(getApplicationContext(), R.color.colorGrey);
            int textColor = ContextCompat.getColor(getApplicationContext(), R.color.colorWhite);
            btn.getBackground().setColorFilter(backgroundColor, PorterDuff.Mode.MULTIPLY);
            btn.setTextColor(textColor);
            btn.setText(tagName);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!resumeTags.contains(tagButtonMap.get(v))) {
                        // set tag
                        int backgroundColor =
                                ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorAccent);
                        btn.getBackground().setColorFilter(backgroundColor,
                                PorterDuff.Mode.MULTIPLY);
                        resumeTags.add(tagButtonMap.get(v));
                    }
                    else {
                        // unset the tag, show resumes even without this tag
                        int backgroundColor =
                                ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorGrey);
                        btn.getBackground().setColorFilter(backgroundColor,
                                PorterDuff.Mode.MULTIPLY);
                        resumeTags.remove(tagButtonMap.get(v));
                    }
                }
            });
            tagsList.addView(btn);
        }
        // set tags and make them non-clickable.
        for(Button btn : tagButtonMap.keySet()) {
            if (btn == null || tagButtonMap.get(btn) == null) {
                continue;
            } else if(resume.getTagList().contains(tagButtonMap.get(btn))) {
                // set tag
                int backgroundColor =
                        ContextCompat.getColor(getApplicationContext(),
                                R.color.colorAccent);
                btn.getBackground().setColorFilter(backgroundColor,
                        PorterDuff.Mode.MULTIPLY);
            }
        }
    }

    public void onHighlightBtnClick(View v) {
        if (resumeView.getVisibility() == ImageView.GONE) {
            resumeView.setVisibility(ImageView.VISIBLE);
        } else
            resumeView.setVisibility(ImageView.GONE);
    }

    public void onDoneBtnClick(View v) {
        resumeView.buildDrawingCache();
        Bitmap bitmap = resumeView.getDrawingCache();
        //save(bitmap);
        // add comments and selected tags to the resume
        final EditText commentField = (EditText) findViewById(R.id.comment_field);
        resume.setRecruiterComments(commentField.getText().toString());
        resume.setTagList(resumeTags);
        alertBox.show();
    }

    public void setUpCandidateEmail(Context context) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.set_candidate_email_dialog, null);
        alertBuilder.setView(dialogView);

        final EditText email = (EditText) dialogView.findViewById(R.id.set_candidate_email);
        alertBuilder.setTitle("Candidate Email");
        alertBuilder.setMessage("Enter email:");

        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (email != null)
                    resume.setCandidateName(email.getText().toString());
            }
        });
        setCandidateEmailAlertBox = alertBuilder.create();
    }

    public void setUpAlertBox(Context context) {
        // setup the alert builder
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Candidate Status");
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
                // Review it and add a rating
                // insert the resume into the database, wait for response, upload it to AWS with the
                // appropriate key
                dataManager.insertResume(resume, new Consumer() {
                    @Override
                    public void accept(Object o) {
                        Resume storedResume = (Resume) o;
                        dataManager.uploadFile(DataManager.getResumeImgKey(storedResume, dataManager.getRecruiter(DataManager.rId)), new File(resumeImgPath),
                                new Consumer() {
                                    @Override
                                    public void accept(Object o) {
                                        // file uploaded, return to stack activity
                                        Intent i = new Intent(EditActivity.this, StackActivity.class);
                                        startActivity(i);
                                    }
                                });
                    }
                });
            }
        });
        alertBox = alertBuilder.create();
    }

    public void save(Bitmap bitmapImage) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(imgFile);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Bitmap load() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(imgFile);
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

