package com.stackd.stackd.db.daos;

public class TagDao {

    private static TagDao tagDao = null;
    //TODO: add the data in the jsonString
    private final String jsonString = "";

    private TagDao() {

    }

    public static TagDao getTagDao() {
        if (tagDao == null) {
            tagDao = new TagDao();
            return tagDao;
        }

        return tagDao;
    }
}
