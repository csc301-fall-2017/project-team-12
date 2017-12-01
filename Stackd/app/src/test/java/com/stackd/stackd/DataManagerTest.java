package com.stackd.stackd;

import com.stackd.stackd.db.DataManager;
import com.stackd.stackd.db.entities.Company;
import com.stackd.stackd.db.entities.Recruiter;
import com.stackd.stackd.db.entities.Resume;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Tanveer on 2017-11-17.
 *
 * Tests to ensure the implementation of <code> DataManager <code/> class.
 */

public class DataManagerTest {
    private DataManager dm;

    @Before
    public void setup() {
        this.dm = DataManager.getDataManager(Long.valueOf(1), Long.valueOf(21), null);
    }

    @After
    public void tearDown() {
        this.dm = null;
    }

    // ---------------------------------------------------------------------------
    @Test
    public void getCompanyTest() {
        Company c = dm.getCompany();
        assertNotNull(c);
        assertEquals(c.getId(), 1);
        assertEquals(c.getName(), "GitHub");
    }

    @Test
    public void getResumeTest() {
        List<Resume> r = dm.getResumes();
        assertNotNull(r);
        assertTrue(Utils.compareResume(Utils.createExpectedResume(), r.get(0)));
    }


    @Test
    public void getRecruiterTest() {
        Recruiter r = dm.getRecruiter();
        assertNotNull(r);
        assertTrue(Utils.compareRecruiter(r, Utils.createExpectedRecruiter()));
    }
}
