package com.bizofapihackathon.campusplacement.externalservices;

import com.bizofapihackathon.campusplacement.api.Entity;
import org.scribe.model.Token;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: umeshprasad
 * Date: 10/02/13
 * Time: 8:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class CredentialsManager {


    Map<String, Token> cachedTokens;

    public Token getAccessToken(Entity.EntityType entityType, String id) {
        return cachedTokens.get(getKey(entityType, id));
    }

    public void saveAccessToken(Entity.EntityType entityType, String id, Token accessToken) {
        cachedTokens.put(getKey(entityType, id), accessToken);
    }

    private String getKey(Entity.EntityType entityType, String id) {
        return entityType + id;
    }



}



