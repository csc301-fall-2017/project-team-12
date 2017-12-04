package com.stackd.stackd;

import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Tag;
import com.stackd.stackd.helpers.ResponseParser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by Tanveer on 2017-11-14.
 *
 * This class tests the json each json parser in
 * <code> ResponseParser <code/> class.
 */

public class ResponseParserTest {

    @Test
    public void parseTagsTest() {
        List<Tag> tags = ResponseParser.parseTagResponse(Utils.getTagResponse());

        assertEquals(tags.get(0).getId(), 10);
        assertEquals(tags.get(0).getName(), "Git");

        assertEquals(tags.get(1).getId(), 12);
        assertEquals(tags.get(1).getName(), "Bash");

        assertEquals(tags.get(2).getId(), 19);
        assertEquals(tags.get(2).getName(), "Python");
    }


    @Test
    public void parseResumeTest() throws NullPointerException {
        List<Resume> actual = ResponseParser.parseResumesResponse(Utils.getResumeResponse());

        /* The expected result */
        List<Resume> expected = new ArrayList<>();
        expected.add(Utils.createExpectedResume());

        assertEquals(expected.size(), actual.size());

        Resume a = actual.get(0);
        Resume e = actual.get(0);

        assertEquals(a.getId(), e.getId());
        assertEquals(a.getRating(), e.getRating());
        assertEquals(a.getRid(), e.getRid());
        assertEquals(a.getCandidateName(), e.getCandidateName());
        assertEquals(a.getCollectionDate(), e.getCollectionDate());
        assertEquals(a.getUrl(), e.getUrl());

        /* Make sure the tags for both resumes are equal */
        assertTrue(Utils.compareTags(a.getTagList().get(0), e.getTagList().get(0)));
        assertTrue(Utils.compareTags(a.getTagList().get(1), e.getTagList().get(1)));
    }

}
