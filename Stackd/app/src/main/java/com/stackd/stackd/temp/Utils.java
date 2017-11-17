package com.stackd.stackd.temp;

import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by Tanveer on 2017-11-17.
 *
 * Temporary class to read the json responses from the json files.
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

    // -----------------------------------------------------------------------------

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
}
