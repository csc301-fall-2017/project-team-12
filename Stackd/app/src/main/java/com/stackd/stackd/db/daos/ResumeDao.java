package com.stackd.stackd.db.daos;

public class ResumeDao {

    private static ResumeDao resumeDao = null;
    //TODO: add the data in the jsonString
    private final String jsonString = "";

    private ResumeDao() {

    }

    public static ResumeDao getResumeDao() {
        if (resumeDao == null) {
            resumeDao = new ResumeDao();
            return resumeDao;
        }

        return resumeDao;
    }
}
