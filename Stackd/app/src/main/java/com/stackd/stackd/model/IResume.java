package com.stackd.stackd.model;

import java.util.List;

/**
 * Created by Musa on 11/6/2017.
 */

public interface IResume {
    /**
     * Attributes of one row from the resume table
     */
    void addReview(int recId, int resId, String date, String comment, int rating);
    void addTag(ITag tag);
}