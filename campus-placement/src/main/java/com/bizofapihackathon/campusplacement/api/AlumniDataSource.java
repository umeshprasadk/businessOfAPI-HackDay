package com.bizofapihackathon.campusplacement.api;

/**
 * Created with IntelliJ IDEA.
 * User: umeshprasad
 * Date: 10/02/13
 * Time: 7:56 AM
 * To change this template use File | Settings | File Templates.
 */

import com.bizofapihackathon.campusplacement.entity.Alumni;
import com.bizofapihackathon.campusplacement.entity.CompanyPositionInfo;
import com.bizofapihackathon.campusplacement.entity.ContactInfo;
import com.bizofapihackathon.campusplacement.entity.MyJsonEntity;
import com.bizofapihackathon.campusplacement.externalservices.LinkedInClient;
import com.bizofapihackathon.campusplacement.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Marker Interface
 */
public class AlumniDataSource {
    private static final Logger LOG = LoggerFactory.getLogger(AlumniDataSource.class);
    private LinkedInClient linkedInClient = new LinkedInClient();
    private static final String[] basicProfileFields = {"first-name", "last-name", "email-address", "location", "relation-to-viewer", "num-connections", "positions", "public-profile-url"};
    private static final String[] responseParams = {"format=json"};
    private static final String GET_CONNECTIONS_URL = "http://api.linkedin.com/v1/people/~/connections:";


    public List<Alumni> getAlumni(int count) {
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

        String getConnectionsRequest = getConnectionsUrl(count);
        LOG.info("Connection url = " + getConnectionsRequest);
        // Create the signed request
        OAuthRequest request = new OAuthRequest(Verb.GET, getConnectionsRequest);
        linkedAuthService.signRequest(accessToken, request);
        // Retrieve the response
        Response response = request.send();
        return parseResponse(response);
    }

    private List<Alumni> parseResponse(Response response) {
        List<Alumni> alumniList = new LinkedList<Alumni>();
        try {
            MyJsonEntity connectionsObject = new MyJsonEntity(new JSONObject(response.getBody()));
            LOG.info("Total Number of connections = " + connectionsObject.getString("_count"));
            MyJsonEntity.MyJsonEntityArray alumnis = connectionsObject.getJSONArray("values");
            Utils.logFormatted(" ALL ALUMNIS ", alumnis);
            for (int i = 0; i < alumnis.length(); i++) {
                MyJsonEntity alumniObject = alumnis.getJSONObject(i);
                Alumni alumni = new Alumni(alumniObject);
                alumniList.add(alumni);
                List<CompanyPositionInfo.PositionHeld> postionsHeld = alumni.getPositions();
                Utils.logFormatted("ALUMNI Contact Info", new ContactInfo(alumni));
                Utils.logFormatted("Postions ", postionsHeld);
            }

        } catch (JSONException e) {
            LOG.error("error", e);
        }
        return alumniList;
    }


    static class ConnectionSearchResult {
        int tootalResults;
        List<Alumni> alumniList;


    }

    private String getUserAuth(String authURL) {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static String getConnectionsUrl(int count) {
        //(id,last-name)
        String fields = StringUtils.join(basicProfileFields, ',');

        StringBuilder sb = new StringBuilder(GET_CONNECTIONS_URL);
        sb.append("(" + fields + ")");
        sb.append("?");
        sb.append("count=").append(count <= 0 ? 5 : count).append("&format=json");
        return sb.toString();
    }


}
