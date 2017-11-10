package com.stackd.stackd.db.daos;

public class RecruiterDao {
    private static RecruiterDao recruiterDao = null;
    //TODO: add the data in the jsonString
    private final String jsonString = "";

    private RecruiterDao() {

    }

    public static RecruiterDao getRecruiterDao() {
        if (recruiterDao == null) {
            recruiterDao = new RecruiterDao();
            return recruiterDao;
        }

        return recruiterDao;
    }
}
