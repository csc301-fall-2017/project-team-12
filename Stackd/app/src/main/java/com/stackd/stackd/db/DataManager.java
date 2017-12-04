package com.stackd.stackd.db;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.stackd.stackd.db.entities.Company;
import com.stackd.stackd.db.entities.Recruiter;
import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Tag;
import com.stackd.stackd.helpers.Consumer;
import com.stackd.stackd.helpers.ResponseParser;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A global class for the whole dataManager resposible for getting the required data
 * from the database to the dataManager users, as objects.
 *
 * Created by Musa on 11/8/2017.
 *
 * -----------------------------------
 * TODO's
 * - set these during authentication
 * - get cid from login activity
 * - get rid from login
 * -----------------------------------
 */

public class DataManager {
    // transfer utility for S3
    private static final String BUCKET_NAME = "stackd-files";
    private TransferUtility transferUtility;
    private static DataManager dataManager = null;
    private Company company;
    private List<Recruiter> recruiters;
    private List<Resume> resumes;
    private String IP = "100.64.187.108"; // EMULATOR= 10.0.2.2

    // Dummy Values
    public static final String cId = "5a20c729a9e27a2096d636ba";
    public static final String rId = "5a20c730a9e27a2096d636bb";

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

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

    public static String COMPANY_RESPONSE_FILE = String.format(
            "%scompany_response.json",
            RESOURCE_FILE);

    public static String RECRUITER_RESPONSE_FILE = String.format(
            "%srecruiter_response.json",
            RESOURCE_FILE);

    public static String RESUME_RESPONSE_FILE = String.format(
            "%sresume_response.json",
            RESOURCE_FILE);


    private DataManager(String companyID, String recruiterId, Context appContext) {
        this.company = requestCompany(companyID);
        this.recruiters = requestRecruiters(companyID);
        this.company.setResumes(requestResumes(companyID, recruiterId));

        // Initialize the Amazon Cognito credentials provider
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                appContext,
                "us-east-1:5a4ae675-8187-4d70-8a69-c0c7eb1c7722", // Identity pool ID
                Regions.US_EAST_1 // Region
        );
        // Create an S3 client
        AmazonS3 s3 = new AmazonS3Client(credentialsProvider);
        transferUtility = new TransferUtility(s3, appContext);
    }

    public static DataManager getDataManager(String companyId, String recruiterId, Context appContext) {
        if (dataManager == null)
            dataManager = new DataManager(companyId, recruiterId, appContext);
        return dataManager;
    }

    /* Return the Company entity given the company id iff the json response is valid,
     * null otherwise.
     **/
    private Company getCompany(String cId) {
        return company;
    }


    /* Return the list of resumes the json response is valid, null otherwise.
     **/
    private List<Resume> getResumes() {
        return resumes;
    }

    public Company getCompany() {
        return this.company;
    }

    /* Return the recruiter with a given rid, if it exists, return null otherwise.
     */
    public Recruiter getRecruiter(String rId) {
        for(Recruiter r : recruiters)
            if(r.getRecId().equals(rId))
                return r;
        return null;
    }

    public List<Tag> getCompanyTags(){
        return this.company.getTags();
    }

    public void addTag(String companyId, Tag tag) {
        // To be implemented...
    }

    public void insertResume(Resume resume) {
        // To be implemented...
        if(this.company.getResumes() == null)
            this.company.setResumes(new ArrayList<Resume>());
        this.company.addResume(resume);
        postResumeRequest("http://" + IP + ":3000/resumes", resume);
    }

    public void addReview(String resId, String date, int rating) {
        // To be implemented...
    }

    private Company requestCompany(String cId) {
        // "cId" is unused for the demo but will be used when making calls to the api
        //String companyAsJsonString = Utils.getCompanyResponse();
        String companyAsJsonString = request(("http://" + IP +":3000/companies"));
        List<Company> companyResult = ResponseParser.parseCompanyResponse(companyAsJsonString);
        Log.d("Company", companyAsJsonString);
        Log.d("Result", companyResult.toString());

        for(Company company : companyResult) {
            if(company.getId().equals(cId)) {
                this.company = company;
                // request company tags
                String tagsAsJsonString = request(("http://" + IP + ":3000/tags"));
                List<Tag> tagResult = ResponseParser.parseTagResponse(tagsAsJsonString);
                if(tagResult != null)
                    company.setTags(tagResult);

                return company;
            }
        }
        return null;
    }

    private List<Recruiter> requestRecruiters(String cId) {
        String recruiterAsJsonString = request(("http://" + IP + ":3000/recruiters"));// Utils.getRecruiterResponse();
        List<Recruiter> result = ResponseParser.parseRecruiterResponse(recruiterAsJsonString);
        List<Recruiter> companyRecruiters = new ArrayList<>();
        for(Recruiter r : result)
            if(r.getCompId().equals(cId))
                companyRecruiters.add(r);
        return companyRecruiters;
    }

    private List<Resume> requestResumes(String cId, String rId) {
        String resumesAsJsonString = request(("http://" + IP + ":3000/resumes"));//Utils.getResumeResponse();
        //TODO: replace this with the commented line
        List<Resume> result = ResponseParser.parseResumeResponse(resumesAsJsonString);
        return result;
    }

    /**
     * Downloads file from S3 bucket with the given key. On completion, notifies the consumer
     * and passes the data in a file.
     * @param key key of the file in S3 bucket
     * @param consumer observer of the current download
     */
    public void downloadFile(String key, final Consumer<File> consumer) {
        final File f = new File(Environment.getExternalStorageDirectory().toString() + "/" + key);
        Log.d("S3", "Downlading to: " + f.getPath());
        TransferObserver observer = transferUtility.download(DataManager.BUCKET_NAME, key, f);
        observer.setTransferListener(new TransferListener() {
            @Override
            public void onStateChanged(int id, TransferState state) {

            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                // when download is complete (or likely to be complete), notify the consumer and pass file to it
                if(bytesTotal > 0 && bytesTotal - bytesCurrent == 0 && f.canRead()) {
                    Log.d("S3", "Download complete/almost complete");
                    consumer.accept(f);
                }
            }

            @Override
            public void onError(int id, Exception ex) {
                Log.d("S3", "Error: " + ex.getLocalizedMessage());
                consumer.accept(null);
            }
        });
    }

    /**
     * Uploads a given file to S3 bucket, assigning it a provided key
     * @param key a key that will identify the file
     * @param newFile a file to be uploaded
     */
    public void uploadFile(String key, File newFile, final Consumer consumer) {
        Log.d("S3", "Uploading a new file");
        TransferObserver observer = transferUtility.upload(BUCKET_NAME, key, newFile);
        observer.setTransferListener(new TransferListener() {
            @Override
            public void onStateChanged(int id, TransferState state) {

            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                Log.d("S3", "Making progress uploading");
                if(bytesCurrent == bytesTotal && bytesTotal > 0)
                    consumer.accept(null);
            }

            @Override
            public void onError(int id, Exception ex) {
                Log.d("S3", ex.getMessage());
            }
        });
    }

    public static String getResumeImgKey(Resume resume, Recruiter recruiter) {
        return resume.getId() + "-" + recruiter.getRecId() + ".jpg";
    }

    private String request(final String url) {
        String responseStr = "";
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Call call = client.newCall(request);
            Response response = call.execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            responseStr = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseStr;
    }

    private void postResumeRequest(final String url, Resume resume) {
        JSONObject jsonResume = ResponseParser.serializeResume(resume);

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, jsonResume.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("POST", response.body().toString());
            }
        });

    }
}
