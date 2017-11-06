package com.stackd.stackd.model;

import com.stackd.stackd.db.entities.TagEntity;

/**
 * Created by Musa on 11/6/2017.
 */

public interface ICompany {
    /**
     * Attributes of one row from the company table
     */
    String getName();
    void setName(String name);
    TagEntity[] getTags();
    void addTag(ITag tag);
}
