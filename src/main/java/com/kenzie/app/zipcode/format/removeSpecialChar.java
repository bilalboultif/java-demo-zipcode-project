package com.kenzie.app.zipcode.format;

// utility class - class that has static helper methods
// example: Math class : Math.min() or Math.random()
public class removeSpecialChar {


    public static String replaceChar(String input) {
        String resultString = "";

        input = input.replaceAll("[^a-zA-Z0-9]", "");
        resultString = input.toLowerCase();
        resultString = resultString.replaceAll("( )+", "");
        return resultString;
    }


}
