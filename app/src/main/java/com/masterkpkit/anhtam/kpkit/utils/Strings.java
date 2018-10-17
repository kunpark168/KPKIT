package com.masterkpkit.anhtam.kpkit.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.masterkpkit.anhtam.kpkit.utils.Objects.isNotNull;
import static com.masterkpkit.anhtam.kpkit.utils.Objects.isNull;
import static com.masterkpkit.anhtam.kpkit.utils.Objects.notNull;

/**
 * @author : longvd
 * @since : 1/6/17
 * Copyright (c) 2017 NTEX JSC. All rights reserved.
 */

public class Strings {

    public static final String EMPTY = "";
    public static final String SIMPLE_EMPTY = "<Empty>";
    public static final String SIMPLE_NULL = "<Null>";
    public static final String INVALID = "#INVALID";

    public static boolean isEmpty(String s) {
        return isNull(s) || equal(s, EMPTY);
    }

    public static boolean isNotEmpty(String s) {
        return isNotNull(s) && s.length() > 0;
    }

    public static boolean notEmpty(String s) {
        return notNull(s) && s.length() > 0;
    }

    public static boolean startsWith(String src, String... prefix) {
        if (src != null) {
            for (String str : prefix) {
                if (src.startsWith(str))
                    return true;
            }
            return false;
        } else {
            return false;
        }
    }

    public static boolean equal(String s1, String s2) {
        return isNotNull(s1) && isNotNull(s2) && s1.equals(s2);
    }

    public static String simple(String s) {
        if (isNull(s)) {
            return SIMPLE_NULL;
        }

        if (isEmpty(s)) {
            return SIMPLE_EMPTY;
        }

        return s;
    }

    public static String separate(String src, int count, String separator) {
        int i = 0;
        ArrayList<String> tmp = new ArrayList<>();
        while (i < src.length()) {
            tmp.add(src.substring(i, Math.min(i + count, src.length())));
            i += count;
        }

        String str = "";
        for (int k = 0; k < tmp.size(); k++) {
            str += tmp.get(k) + (k != tmp.size() - 1 ? separator : "");
        }

        return str;
    }

    public static String formatNamAAccount(String src) {
        try {
            return src.substring(0, 3) + " " + src.substring(3, 10) + " " + src.substring(10, 15);
        } catch (Exception e) {
            return formatNormalAccount(src);
        }
    }

    public static String formatNormalAccount(String src) {
        try {
            return separate(src, 4, " ");
        } catch (Exception e) {
            return src;
        }
    }

    private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
            'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
            'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
            'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
            'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
            'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
            'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
            'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
            'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
            'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
            'ữ', 'Ự', 'ự',};

    private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
            'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
            'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
            'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
            'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
            'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
            'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
            'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
            'U', 'u', 'U', 'u',};

    private static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

    public static String removeAccent(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }

    public static boolean isContain(String key, String[] map) {
        if (map != null) {
            for (String item : map) {
                if (key.equals(item)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static int countMatch(String src, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(src);

        int count = 0;
        while (matcher.find())
            count++;

        return count;
    }
}
