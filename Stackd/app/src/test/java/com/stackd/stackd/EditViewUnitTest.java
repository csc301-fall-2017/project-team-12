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
public class EditViewUnitTest {
    //

    @Test
    public void BuildResumeTest() throws Exception {
        DataManager dm = DataManager.getDataManager(Long.valueOf(1), Long.valueOf(21), null);
        List<Resume> originalResumes = dm.getResumes();
//        Resume newResume = new Resume.Builder()
//                .id(new Long(1))
//                .rid(dm.getRecruiter().getRecId())
//                .tagList(dm.getCompanyTags())
//                .url("http://localhost:8080/Desktop/Resumes/10.pdf")
//                .collectionDate(new SimpleDateFormat("DD-MM-YYYY").toString())
//                .candidateName("Ali May")
//                .build();

//         -- <code> DataManager.addReview</code> is not implemented yet. --
//         dm.addReview(newResume.getId(), newResume.getCollectionDate(), newResume.getRating());

//        originalResumes.add(newResume);
        List<Resume> updatedResumes = dm.getResumes();

        assertTrue(Utils.compareResume(originalResumes.get(0),
                updatedResumes.get(0)));

//        for (int i = 0; i < originalResumes.size(); i++) {
//            assertTrue(Utils.compareResume(originalResumes.get(i),
//                    updatedResumes.get(i)));
//        }
    }
}