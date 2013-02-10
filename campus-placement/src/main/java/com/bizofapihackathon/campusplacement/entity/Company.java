package com.bizofapihackathon.campusplacement.entity;

/**
 * Created with IntelliJ IDEA.
 * User: umeshprasad
 * Date: 10/02/13
 * Time: 7:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class Company {

    //private static final String[] basicProfileFields = {"id", "name", "universal-name", "company-type", "website-url", "industries", "status", "employee-count-range", "locations", "num-followers"};

    private final MyJsonEntity companyObject;

    public Company(MyJsonEntity companyObject) {
        this.companyObject = companyObject;
    }

    public String getId() {
        return companyObject.getString("id");
    }

    public String getEmployeeCount() {
        return companyObject.getString("employee-count-range");
    }

    public String getName() {
        return companyObject.getString("name");
    }

    public String getWebsite() {
        return companyObject.getString("website-url");
    }

    public String getCompanyType() {
        return companyObject.getString("company-type");
    }

    public String getLocation() {
        return companyObject.getString("locations");
    }

    public int getNumFollowers() {
        return companyObject.getInt("num-followers");
    }

    public String toString() {
        return getId() + ", name = " + getName() + ", website = " + getWebsite() + ", type = " + getCompanyType() + ", lcoation = " +
                getLocation() + ", followers = " + getNumFollowers();

    }

}
