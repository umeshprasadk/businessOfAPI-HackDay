package com.bizofapihackathon.campusplacement.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: umeshprasad
 * Date: 10/02/13
 * Time: 7:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyPositionInfo {

    public List<PositionHeld> positionsList = new LinkedList<PositionHeld>();

    public static class PositionHeld {
        MyJsonEntity positionHeld;

        public PositionHeld(MyJsonEntity jsonObject) {
            positionHeld = jsonObject;
        }

        public String getCompanyId() {
            return positionHeld.getJSONObject("company").getString("id");
        }

        public boolean isCurrent() {
            return positionHeld.getBoolean("isCurrent");

        }

        public String getJobTitle() {
            return positionHeld.getString("title");
        }

        public String toString() {
            return getCompanyId() + ", " + isCurrent() +
                    ", " + getJobTitle();
        }
    }
}
