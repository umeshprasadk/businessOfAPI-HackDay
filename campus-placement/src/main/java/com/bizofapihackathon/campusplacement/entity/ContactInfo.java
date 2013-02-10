package com.bizofapihackathon.campusplacement.entity;

/**
 * Created with IntelliJ IDEA.
 * User: umeshprasad
 * Date: 10/02/13
 * Time: 7:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class ContactInfo {
    public final String firstName;
    public final String lastName;
    public final String email;

    public ContactInfo(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public ContactInfo(Alumni alumni) {
        this.firstName = alumni.getFirstName();
        this.lastName = alumni.getLastName();
        this.email = alumni.getEmail();
    }

    public String toString() {
        return "Name = " + firstName + " " + lastName + ", email = " + email;
    }
}
