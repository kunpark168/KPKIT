package com.masterkpkit.anhtam.kpkit.utils;

import java.util.regex.Pattern;

import static com.masterkpkit.anhtam.kpkit.utils.Strings.startsWith;

/**
 * @author : IBN-5100
 * @since : July
 * Copyright (c) 2017 NTEX JSC. All rights reserved.
 */

public class Phones {

    public enum Locale {
        VN
    }

    public enum NetworkProvider {
        UNKNOWN, VINAPHONE, MOBIFONE, VIETTEL, GMOBILE, HTMOBILE, VIETNAMMOBILE
    }

    public static boolean isValidPhoneNumber(String src, Locale locale) {
        if (src == null) {
            return false;
        } else {
            src = simplePhone(src);
            try {
                long l = Long.parseLong(src);
            } catch (Exception e) {
                return false;
            }
            switch (locale) {
                case VN:
                    return src.startsWith("0") && ((src.startsWith("01") && src.length() == 11) || (!src.startsWith("01") && src.startsWith("0") && src.length() == 10));
                default:
                    return false;
            }
        }
    }

    public static String simplePhone(String src) {
        String phone = src;
        if (phone != null) {
            if (src.startsWith("0084"))
                phone = src.replaceFirst(Pattern.quote("0084"), "0");
            else if (src.startsWith("+84"))
                phone = src.replaceFirst(Pattern.quote("+84"), "0");
        }
        return phone;
    }

    private static String getFormattedPhone(String src) {
        if (src.startsWith("0084"))
            return src.replace("0084", "0");
        else if (src.startsWith("+84"))
            return src.replace("+84", "0");
        else
            return src;
    }

    public static NetworkProvider getNetworkProvider(String src) {
        if (isValidPhoneNumber(src, Locale.VN)) {
            String phone = getFormattedPhone(src);
            if (startsWith(phone, "096", "097", "098", "0162", "0163", "0164", "0165", "0166", "0167", "0168", "0169", "086"))
                return NetworkProvider.VIETTEL;
            if (startsWith(phone, "091", "094", "0123", "0124", "0125", "0127", "0129", "088"))
                return NetworkProvider.VINAPHONE;
            if (startsWith(phone, "090", "093", "0120", "0121", "0122", "0126", "0128", "089"))
                return NetworkProvider.MOBIFONE;
            if (startsWith(phone, "099", "0199"))
                return NetworkProvider.GMOBILE;
            if (startsWith(phone, "092", "0188", "0186"))
                return NetworkProvider.HTMOBILE;
        }
        return NetworkProvider.UNKNOWN;
    }

    public static boolean validationPhoneNumber(String phone) {
        return phone.length() > 9 && phone.length() < 14;
    }
}
