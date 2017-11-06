package com.stackd.stackd.model;

import java.util.List;

/**
 * Created by Musa on 11/6/2017.
 */

public interface ICompany {
    /**
     * Attributes of one row from the company table
     */
    String getName();
    void setName(String name);
    List<ITag> getTags();
    void addTag(ITag tag);
    String getExportDestination();
}
