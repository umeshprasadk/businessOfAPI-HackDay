package com.bizofapihackathon.campusplacement.api;

import com.bizofapihackathon.campusplacement.entity.*;
import com.bizofapihackathon.campusplacement.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: umeshprasad
 * Date: 10/02/13
 * Time: 1:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class CampusRecruitmentLeadGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(CampusRecruitmentLeadGenerator.class);

    public static void main(String[] args) throws Exception {
//        testCompanyUrl();
        testGetConnections();
    }

    public static void testGetConnections() throws Exception {
        AlumniDataSource alumniDataSource = new AlumniDataSource();
        CompanyDataSource companyDataSource = new CompanyDataSource();
        List<Alumni> alumniList = alumniDataSource.getAlumni(1);
        Map<String, CampusRecruitmentLead> recruitmentLeads = new HashMap<String, CampusRecruitmentLead>();
        for (Alumni alumni : alumniList) {
            List<CompanyPositionInfo.PositionHeld> positionHeldList = alumni.getPositions();
            for (CompanyPositionInfo.PositionHeld positionHeld : positionHeldList) {
                String companyId = positionHeld.getCompanyId();
                if (StringUtils.isNotBlank(companyId)) {
                    Company company = companyDataSource.getCompanyInfo(companyId);
                    aggregateLeads(company, alumni, recruitmentLeads);
                    Utils.logFormatted("Campus Recruitment Leads = ", recruitmentLeads);
                }
            }
        }

        LOG.info("Best leads", getBestLeads(recruitmentLeads));
    }


    public static void aggregateLeads(Company company, Alumni alumni, Map<String, CampusRecruitmentLead> leads) {

        Utils.logFormatted("Company =", company);
        CampusRecruitmentLead lead = leads.get(company.getId());
        if (lead == null) {
            lead = new CampusRecruitmentLead(company, alumni);
        }
        lead.getAllAlumniLeads().add(alumni);
        leads.put(company.getId(), lead);
    }


    public static Map<String, List<ContactInfo>> getBestLeads(Map<String, CampusRecruitmentLead> leads) {
        Map<String, List<ContactInfo>> companyLeads = new HashMap<String, List<ContactInfo>>();
        for (Map.Entry<String, CampusRecruitmentLead> leadEntry : leads.entrySet()) {
            CampusRecruitmentLead lead = leadEntry.getValue();
            List<ContactInfo> bestLeads = getBestLeads(lead.companyId, lead.getAllAlumniLeads(), 3);
            companyLeads.put(leadEntry.getKey(), bestLeads);
        }
        return companyLeads;
    }

    private static List<ContactInfo> getBestLeads(String companyId, List<Alumni> alumniList, int size) {
        List<ContactInfo> bestLeads = new LinkedList<ContactInfo>();
        for (Alumni alumni : alumniList) {
            if (bestLeads.size() == size) {
                break;
            }
            CompanyPositionInfo.PositionHeld currentPosition = alumni.getCurrentPosition();
//            if (StringUtils.equalsIgnoreCase(companyId, currentPosition.getCompanyId())) {
            bestLeads.add(new ContactInfo(alumni));
//            }

        }
        return bestLeads;

    }

    public static void testCompanyUrl() {
        String companyId = "1337";
        CompanyDataSource companyDataSource = new CompanyDataSource();
        companyDataSource.getCompanyInfo(companyId);
    }
}
