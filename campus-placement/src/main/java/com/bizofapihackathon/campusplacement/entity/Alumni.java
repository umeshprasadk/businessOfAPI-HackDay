package com.bizofapihackathon.campusplacement.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: umeshprasad
 * Date: 10/02/13
 * Time: 7:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class Alumni {

    private final MyJsonEntity alumniObject;

    public Alumni(MyJsonEntity alumniObject) {
        this.alumniObject = alumniObject;
    }

    public String getFirstName() {
        return alumniObject.getString("firstName");
    }

    public String getLastName() {
        return alumniObject.getString("lastName");
    }

    public String getEmail() {
        return alumniObject.getString("email-address");
    }
    /*
                String city = alumni.getJSONObject("location").getString("name");
                int numConnection = alumni.getInt("numConnections");

     */

    public List<CompanyPositionInfo.PositionHeld> getPositions() {
        MyJsonEntity positionObject = alumniObject.getJSONObject("positions");
        List<CompanyPositionInfo.PositionHeld> positionsList = new LinkedList<CompanyPositionInfo.PositionHeld>();
        MyJsonEntity.MyJsonEntityArray positions = positionObject.getJSONArray("values");
        for (int i = 0; i < positions.length(); i++) {
            MyJsonEntity positionHeldJson = positions.getJSONObject(i);
            CompanyPositionInfo.PositionHeld positionHeld = new CompanyPositionInfo.PositionHeld(positionHeldJson);
            positionsList.add(positionHeld);
        }
        return positionsList;
    }

    public CompanyPositionInfo.PositionHeld getCurrentPosition() {
        List<CompanyPositionInfo.PositionHeld> allPositions = getPositions();
        for (CompanyPositionInfo.PositionHeld positionHeld : allPositions) {
            if (positionHeld.isCurrent()) {
                return positionHeld;
            }
        }
        return null;
    }

    public ContactInfo getContactInfo() {
        return new ContactInfo(this);
    }

    public String toString() {
        return "firstName = " + getFirstName() + ", lastname = " + getLastName();//+ ", city = " + city + ", numConnections = " + numConnection;
    }
}
