package com.stackd.stackd.model;

import java.util.List;

/**
 * Created by Musa on 11/6/2017.
 */

public interface Company {
    String getName();
    void setName(String name);
    List<Tag> getTags();
    void addTag(Tag tag);

}
