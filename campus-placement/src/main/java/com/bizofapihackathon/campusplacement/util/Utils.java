package com.bizofapihackathon.campusplacement.util;

/**
 * Created by IntelliJ IDEA.
 * User: umeshprasad
 * Date: 10/02/13
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    private static final String start = "==============================================================";
    private static final String emptySpace = start.replace('=', ' ');

    public static void logFormatted(String header, Object text) {
        printHeader(header);
        System.out.println(text);
        System.out.println(start);
    }

    private static void printHeader(String header) {
        System.out.println(start);
        System.out.println(emptySpace);
        System.out.println(header);
//        System.out.println(emptySpace + start);
    }


}
