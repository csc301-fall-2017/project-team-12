package com.stackd.stackd;

import android.app.AlertDialog;

import com.stackd.stackd.db.DataManager;
import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Tag;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class editViewUnitTest {
    @Test
    public void BuildResumeTest() throws Exception {

        Long cId = new Long(1);
        Long rId = new Long(21);
        DataManager dataManager = DataManager.getDataManager(cId, rId);

        List<Resume> originalResumes = dataManager.getResumes();
        final Resume.Builder resumeBuilder = new Resume.Builder();
        resumeBuilder.id(new Long(1));
        resumeBuilder.rid(dataManager.getRecruiter().getRecId());
        System.out.print("Company: " + dataManager.getCompany());
        resumeBuilder.tagList(dataManager.getCompanyTags());
        resumeBuilder.url("http://localhost:8080/Desktop/Resumes/10.pdf");
        resumeBuilder.recruiterComments("Great fit.");
        resumeBuilder.collectionDate(new SimpleDateFormat("DD-MM-YYYY").toString());
        resumeBuilder.candidateName("Ali May");

        Resume newResume = resumeBuilder.build();
        dataManager.addReview(newResume.getId(), newResume.getCollectionDate(), newResume.getRating());

        originalResumes.add(newResume);

        List<Resume> updatedResumes = dataManager.getResumes();
        for (int i = 0; i < originalResumes.size(); i ++) {
            assertEquals(originalResumes.get(i), updatedResumes.get(i));
        }
        assertEquals(1, 1);
    }
}