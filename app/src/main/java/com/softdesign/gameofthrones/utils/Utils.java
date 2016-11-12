package com.softdesign.gameofthrones.utils;

public class Utils {

    public static int getIdFromUrl(String url) {
        return Integer.parseInt(getIdFormUrl(url));
    }

    private static String getIdFormUrl(String url) {

        String s = "";
        for (int i = url.length() -1; i >= 0; i--) {
            if (Character.isDigit(url.charAt(i))) {
                s = url.charAt(i) + s;
            } else {
                break;
            }
        }

        if (s.trim().isEmpty()) {
            s = "0";
        }

        try {
            if (Integer.parseInt(s) > 0) {
                return s;
            } else {
                return "0";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "0";
        }
    }
}