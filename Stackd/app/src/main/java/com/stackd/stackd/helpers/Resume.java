package com.stackd.stackd.helpers;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Resume {
    /**
     * A Resume with the PDF and candidate information
     */
    private String fileName;
    private String email;
    private DateFormat date;
    private HashMap<String, Boolean> tagList;
    public HashMap<String, ArrayList<String>> recruiterToComments;

    public Resume(String fileName, String email, DateFormat date, HashMap<String, Boolean>  tagList) {
        this.fileName = fileName;
        this.email = email;
        this.date = date;
        this.tagList = tagList;
        recruiterToComments = new HashMap<String, ArrayList<String>>();
    }

    public Resume(String fileName) {
        this.fileName = fileName;
        this.email = "";
        this.date = null;
        this.tagList = new HashMap<String, Boolean> ();
        recruiterToComments = new HashMap<String, ArrayList<String>>();
    }

    public Resume() {
        this.fileName = "";
        this.email = "";
        this.date = null;
        this.tagList = new HashMap<String, Boolean> ();
        recruiterToComments = new HashMap<String, ArrayList<String>>();
    }



    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateFormat getDate() {
        return date;
    }

    public void setDate(DateFormat date) {
        this.date = date;
    }

    public HashMap<String, Boolean>  getTagList() {
        return tagList;
    }

    public void setTagList(HashMap<String, Boolean>  tagList) {
        this.tagList = tagList;
    }

    public HashMap<String, ArrayList<String>> getReviewToComments() {
        return recruiterToComments;
    }

    public void addComments(String recruiterId, String comments){

        ArrayList<String> localComments = new ArrayList<>();
        if (!recruiterToComments.containsKey(recruiterId)) {
            recruiterToComments.put(recruiterId, localComments);
        }
        localComments.add(comments);
        recruiterToComments.put(recruiterId, localComments );

    }

    public void addTags(String tag) {
        if (tagList.containsKey(tag)){
            tagList.put(tag, !(tagList.get(tag)));
        } else {
            tagList.put(tag, Boolean.TRUE);
        }
    }
}
