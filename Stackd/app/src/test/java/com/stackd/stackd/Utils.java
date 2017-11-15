package com.stackd.stackd;

import android.util.Log;

import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Tag;
import com.stackd.stackd.helpers.ResponseParser;

import junit.framework.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.*;
import org.json.JSONException;

/**
 * Created by Tanveer on 2017-11-14.
 *
 * Methods to help efficiently carry the unit tests
 */

public class Utils {
    private static String fs = File.separator;
    private static String PROJECT_PATH = new File(".").getPath();
    private static String RESOURCE_FILE = new Formatter(new StringBuilder(), Locale.CANADA)
            .format("%1$1s%2$1sapp" +
                            "%2$1ssrc" +
                            "%2$1stest" +
                            "%2$1sjava" +
                            "%2$1scom" +
                            "%2$1sstackd" +
                            "%2$1sstackd" +
                            "%2$1sresources" +
                            "%2$1s",
                    PROJECT_PATH, fs).toString();

    /* Path of the json files containing a dummy response from the api */
    private static String TAG_RESPONSE_FILE = String.format(
            "%stag_response.json",
            RESOURCE_FILE);

    private static String COMPANY_RESPONSE_FILE = String.format(
            "%scompany_response.json",
            RESOURCE_FILE);

    private static String RECRUITER_RESPONSE_FILE = String.format(
            "%srecruiter_response.json",
            RESOURCE_FILE);

    private static String RESUME_RESPONSE_FILE = String.format(
            "%sresume_response.json",
            RESOURCE_FILE);

    // -------------------------------------------------------------

    private static String getResponse(String filePath) {
        try {
            InputStream inputStream = new FileInputStream(filePath);
            String response = IOUtils.toString(inputStream);
            inputStream.close();

            return response;
        } catch (IOException e) {
            Log.e(e.getMessage(), e.getStackTrace().toString());
            return null;
        }
    }

    public static String getTagResponse() {
        return getResponse(TAG_RESPONSE_FILE);
    }

    public static String getCompanyResponse() {
        return getResponse(COMPANY_RESPONSE_FILE);
    }

    public static String getRecruiterResponse() {
        return getResponse(RECRUITER_RESPONSE_FILE);
    }

    public static String getResumeResponse() {
        return getResponse(RESUME_RESPONSE_FILE);
    }

    public static Resume createExpectedResume() throws JSONException {
        List<Tag> tags = ResponseParser.parseTagResponse(Utils.getTagResponse());
        tags.remove(tags.size() - 1);

        String url = "http://localhost:8080/Desktop/Resumes/9.pdf";
        String comments = "Perfect fit for the position!";

        return new Resume.Builder()
                .id(9)
                .rid(21)
                .rating(10)
                .collectionDate("09/22/2009")
                .candidateName("Nate Diaz")
                .tagList(tags)
                .url(url)
                .recruiterComments(comments)
                .build();
    }

    public static boolean compareTags(Tag t1, Tag t2) {
        return ((t1.getId() == t2.getId()) && (t1.getName() == t2.getName()));
    }
}
