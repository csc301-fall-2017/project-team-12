package com.stackd.stackd.db.daos;

public class CompanyDao {

    private static CompanyDao companyDao = null;
    //TODO: add the data in the jsonString
    private final String jsonString = "";

    private CompanyDao() {

    }

    public static CompanyDao getCompanyDao() {
        if (companyDao == null) {
            companyDao = new CompanyDao();
            return companyDao;
        }

        return companyDao;
    }


}
