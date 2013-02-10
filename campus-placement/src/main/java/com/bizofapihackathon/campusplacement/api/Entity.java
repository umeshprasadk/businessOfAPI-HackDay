package com.bizofapihackathon.campusplacement.api;

import com.bizofapihackathon.campusplacement.entity.ContactInfo;

/**
 * Created with IntelliJ IDEA.
 * User: umeshprasad
 * Date: 10/02/13
 * Time: 7:58 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Entity {

    public EntityType getType();

    public String getId();

    public String getName();

    public ContactInfo getContactInfo();

    public String getUrl();

    public static enum EntityType {
        Person, Company
    }
}
