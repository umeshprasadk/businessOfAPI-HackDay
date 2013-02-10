package com.bizofapihackathon.campusplacement.externalservices;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.oauth.OAuthService;

/**
 * Created with IntelliJ IDEA.
 * User: umeshprasad
 * Date: 10/02/13
 * Time: 7:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedInClient {

    public OAuthService getProfileScope() {
        APICredentials scribe = APICredentials.Scribe;
        return new ServiceBuilder()
                .provider(LinkedInApi.class)
                .apiKey(scribe.getAPIKey())
                .apiSecret(scribe.getSecretKey())
                .scope("r_basicprofile")
                .scope("r_emailaddress")
                .scope("r_contactinfo")
                .scope("r_fullprofile")
                .scope("r_network")
                .callback("url")
                .build();
    }


    public OAuthService getCompany() {
        APICredentials scribe = APICredentials.Scribe;
        return new ServiceBuilder()
                .provider(LinkedInApi.class)
                .apiKey(scribe.getAPIKey())
                .apiSecret(scribe.getSecretKey())
                .callback("url")
                .build();
    }

    public static enum LinkedScope {

        Basicprofile("r_basicprofile"), Email("r_emailaddress");
        private final String scope;

        private LinkedScope(String scope) {
            this.scope = scope;
        }

        public String scope() {
            return scope;
        }
    }

    public enum APICredentials {

        Scribe("CiEgwWDkA5BFpNrc0RfGyVuSlOh4tig5kOTZ9q97qcXNrFl7zqk-Ts7DqRGaKDCV", "dhho4dfoCmiQXrkw4yslork5XWLFnPSuMR-8gscPVjY4jqFFHPYWJKgpFl4uLTM6");
        private final String apiKey;
        private final String apiSecret;

        private APICredentials(String apiKey, String secretKey) {
            this.apiKey = apiKey;
            this.apiSecret = secretKey;

        }

        public String getAPIKey() {
            return apiKey;
        }

        public String getSecretKey() {
            return apiSecret;
        }
    }


}
