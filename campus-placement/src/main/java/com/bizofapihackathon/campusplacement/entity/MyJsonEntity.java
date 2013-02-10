package com.bizofapihackathon.campusplacement.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by IntelliJ IDEA.
 * User: umeshprasad
 * Date: 10/02/13
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyJsonEntity {
    private final JSONObject jsonObject;

    public MyJsonEntity(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getString(String fieldName) {
        try {
            return jsonObject.getString(fieldName);
        } catch (JSONException e) {

        }
        return "";
    }


    public MyJsonEntity getJSONObject(String fieldName) {
        try {
            return new MyJsonEntity(jsonObject.getJSONObject(fieldName));
        } catch (JSONException e) {

        }
        return null;
    }

    public MyJsonEntityArray getJSONArray(String fieldName) {
        try {
            return new MyJsonEntityArray(jsonObject.getJSONArray(fieldName));
        } catch (JSONException e) {

        }
        return null;
    }

    public int getInt(String fieldName) {
        try {
            return jsonObject.getInt(fieldName);
        } catch (JSONException e) {

        }
        return Integer.MIN_VALUE;
    }

    public boolean getBoolean(String fieldName) {
        try {
            return jsonObject.getBoolean(fieldName);
        } catch (JSONException e) {

        }
        return false;
    }

    public String toString() {
        return jsonObject.toString();
    }

    public static class MyJsonEntityArray {

        private final JSONArray jsonArray;

        public MyJsonEntityArray(JSONArray jsonArray) {
            this.jsonArray = jsonArray;
        }

        public MyJsonEntity getJSONObject(int i) {
            try {
                return new MyJsonEntity(jsonArray.getJSONObject(i));
            } catch (JSONException e) {
            }
            return null;
        }

        public boolean getBoolean(int i) {
            try {
                return jsonArray.getBoolean(i);
            } catch (JSONException e) {
            }
            return false;
        }


        public int length() {
            return jsonArray.length();
        }

        public String toString() {
            return jsonArray.toString();
        }

    }

}
