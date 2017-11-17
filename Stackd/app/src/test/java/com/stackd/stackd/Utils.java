package com.stackd.stackd;

import android.util.Log;

import com.stackd.stackd.db.entities.Recruiter;
import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Tag;
import com.stackd.stackd.helpers.ResponseParser;

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

    // ---------------------------------------------------------------------------

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

    static String getTagResponse() {
        return getResponse(TAG_RESPONSE_FILE);
    }

    static String getCompanyResponse() {
        return getResponse(COMPANY_RESPONSE_FILE);
    }

    static String getRecruiterResponse() {
        return getResponse(RECRUITER_RESPONSE_FILE);
    }

    static String getResumeResponse() {
        return getResponse(RESUME_RESPONSE_FILE);
    }

    static boolean compareTags(Tag t1, Tag t2) {
        return ((t1.getId() == t2.getId()) &&
                (t1.getName().equals(t2.getName())));
    }

    static boolean compareRecruiter(Recruiter r1, Recruiter r2) {
        return r1.getRecId() == r2.getRecId() &&
                r1.getCompId() == r2.getCompId() &&
                r1.getFirstName().equals(r2.getFirstName()) &&
                r1.getLastName().equals(r2.getLastName()) &&
                r1.getEmail().equals(r2.getEmail());

    }

    static boolean compareResume(Resume r1, Resume r2) {
        return r1.getId() == r2.getId() &&
                r1.getRid() == r2.getRid() &&
                r1.getCandidateName().equals(r2.getCandidateName()) &&
                r1.getCollectionDate().equals(r2.getCollectionDate()) &&
                r1.getRating() == r2.getRating();
    }

    static Recruiter createExpectedRecruiter() {
        return new Recruiter.Builder()
                .recId(21)
                .compId(1)
                .email("lisaroberts@github.com")
                .firstName("Lisa")
                .lastName("Roberts")
                .build();

    }

    static Resume createExpectedResume() throws NullPointerException {
        String response = Utils.getTagResponse();
        if (response == null) throw new NullPointerException();

        List<Tag> tags = ResponseParser.parseTagResponse(response);
        tags.remove(tags.size() - 1);

        String url = "http://localhost:8080/Desktop/Resumes/9.pdf";
        String comments = "Perfect fit for the position!";

        return new Resume.Builder()
                .id(9)
                .rid(21)
                .rating(1)
                .collectionDate("09/22/2009")
                .candidateName("Nate Diaz")
                .tagList(tags)
                .url(url)
                .recruiterComments(comments)
                .build();
    }
}
