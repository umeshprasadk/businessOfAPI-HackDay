package com.bizofapihackathon.campusplacement.api;

import com.bizofapihackathon.campusplacement.entity.Company;
import com.bizofapihackathon.campusplacement.entity.MyJsonEntity;
import com.bizofapihackathon.campusplacement.externalservices.LinkedInClient;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: umeshprasad
 * Date: 10/02/13
 * Time: 7:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyDataSource {
    private static final Logger LOG = LoggerFactory.getLogger(CompanyDataSource.class);

    LinkedInClient linkedInClient = new LinkedInClient();

    public Company getCompanyInfo(String companyId) {
        OAuthService linkedAuthService = linkedInClient.getCompany();
        org.scribe.model.Token requestToken = linkedAuthService.getRequestToken();
        LOG.debug("request Token ={}", requestToken);
        String authURL = linkedAuthService.getAuthorizationUrl(requestToken);
        LOG.info("authURL ={}", authURL);
        String userAuth = getUserAuth(authURL);
        Verifier verifier = new Verifier(userAuth);
        org.scribe.model.Token accessToken = linkedAuthService.getAccessToken(requestToken, verifier);

        String getConnectionsRequest = getConnectionsUrl(companyId);
        LOG.debug("Connection url = " + getConnectionsRequest);
        // Create the signed request
        OAuthRequest request = new OAuthRequest(Verb.GET, getConnectionsRequest);
        linkedAuthService.signRequest(accessToken, request);
        // Retrieve the response
        Response response = request.send();
        return parseResponse(response);
    }

    private Company parseResponse(Response response) {
        try {
            return new Company(new MyJsonEntity(new JSONObject(response.getBody())));
        } catch (JSONException e) {
            LOG.error("", e);
        }
        return null;
    }

    private String getUserAuth(String authURL) {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }


    private static final String[] basicProfileFields = {"id", "name", "universal-name", "company-type", "website-url", "industries", "status", "employee-count-range", "locations", "num-followers"};
    private static final String[] responseParams = {"format=json"};
    private static final String GET_CONNECTIONS_URL = "http://api.linkedin.com/v1/companies/";


    private static String getConnectionsUrl(String companyId) {
        //(id,last-name)
        String fields = StringUtils.join(basicProfileFields, ',');

        StringBuilder sb = new StringBuilder(GET_CONNECTIONS_URL);
        sb.append(companyId);
        sb.append(":");

        sb.append("(" + fields + ")");
        sb.append("?");
        sb.append("format=json");
        return sb.toString();
    }

    /*
     public List<Alumni> getAlumni() {
        // Create the Auth service.
        OAuthService linkedAuthService = linkedInClient.getProfileScope();
        // Retrieve the accessToken .    (access token encapsulates 1. id of person, type of profile, )
        Token requestToken = linkedAuthService.getRequestToken();
        LOG.info("request Token ={}", requestToken);
        String authURL = linkedAuthService.getAuthorizationUrl(requestToken);
        LOG.info("authURL ={}", authURL);
        String userAuth = getUserAuth(authURL);
        Verifier verifier = new Verifier(userAuth);
        Token accessToken = linkedAuthService.getAccessToken(requestToken, verifier);

        String getConnectionsRequest = getConnectionsUrl();
        LOG.info("Connection url = " + getConnectionsRequest);
        // Create the signed request
        OAuthRequest request = new OAuthRequest(Verb.GET, getConnectionsRequest);
        linkedAuthService.signRequest(accessToken, request);
        // Retrieve the response
        Response response = request.send();
        parseResponse(response);
//        LOG.info(response.getBody());
        return null;
    }
     */
}
