package dallasapps.nsunstracker.util;

/**
 * Created by katy on 7/9/17.
 */

public class StringFormatter {


    public static String formatWeight(double weight, boolean isKg) {
        String unformattedWeight = Double.toString(weight);
        if (isKg) {
            if(unformattedWeight.contains(".")) {
                String[] splitString = unformattedWeight.split("[.]");
                if (splitString[1].startsWith("0")) {
                    return splitString[0];
                }
                else {
                    return splitString[0] + "." + splitString[1].substring(0,0);
                }
            }
            else {
                return unformattedWeight;
            }
        }
        if(unformattedWeight.contains(".")) {
            String[] splitString = unformattedWeight.split("[.]");
            return splitString[0];
        }
        else {
            return unformattedWeight;
        }
    }

    public static String formatWeight(String unformattedWeight, boolean isKg) {
        if(unformattedWeight == null) {
            return null;
        }
        if (isKg) {
            if(unformattedWeight.contains(".")) {
                String[] splitString = unformattedWeight.split("[.]");
                if (splitString[1].startsWith("0")) {
                    return splitString[0];
                }
                else {
                    return splitString[0] + "." + splitString[1].substring(0,0);
                }
            }
            else {
                return unformattedWeight;
            }
        }
        else {
            if(unformattedWeight.contains(".")) {
                String[] splitString = unformattedWeight.split("[.]");
                return splitString[0];
            }
            else {
                return unformattedWeight;
            }
        }
    }
}
