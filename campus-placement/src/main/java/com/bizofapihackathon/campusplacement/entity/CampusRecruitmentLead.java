package com.bizofapihackathon.campusplacement.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: umeshprasad
 * Date: 10/02/13
 * Time: 3:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class CampusRecruitmentLead {

    public final String companyId;
    public final Company company;

    private final List<Alumni> allAlumniLeads = new LinkedList<Alumni>();

    public CampusRecruitmentLead(Company company, Alumni alumni) {
        this.company = company;
        companyId = company.getId();
        allAlumniLeads.add(alumni);
    }

    public List<Alumni> getAllAlumniLeads() {
        return allAlumniLeads;
    }

    public String toString() {
        return companyId + ", Alumnis = " + allAlumniLeads;
    }
}
