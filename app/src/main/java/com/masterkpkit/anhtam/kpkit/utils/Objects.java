package com.masterkpkit.anhtam.kpkit.utils;

/**
 * @author : longvd
 * @since : 1/6/17
 * Copyright (c) 2017 NTEX JSC. All rights reserved.
 */

public class Objects {

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isNotNull(Object... obj) {
        for (Object object : obj) {
            if (isNull(object))
                return false;
        }
        return true;
    }

    public static String toString(Object obj) {
        return isNotNull(obj) ? obj.toString() : Strings.SIMPLE_NULL;
    }

    public static String simple(Class t) {
        return t.getSimpleName();
    }

    public static boolean notNull(Object obj) {
        return obj != null;
    }
}
