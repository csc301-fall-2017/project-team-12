package com.stackd.stackd.model;

import java.util.List;

/**
 * Created by Musa on 11/6/2017.
 */

public interface Resume {
    boolean hasTag(Tag tag);
    void addReview(int recId, int resId, String date, String comment, int rating);
    void addTag(Tag tag);
    List<Tag> getResumeTags();
}