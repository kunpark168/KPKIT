package com.masterkpkit.anhtam.kpkit.utils;

import android.util.Log;

import vn.ntex.uat.App;

import static vn.ntex.uat.utils.Objects.isNotNull;
import static vn.ntex.uat.utils.Strings.simple;

/**
 * @author : longvd
 * @since : 1/6/17
 * Copyright (c) 2017 NTEX JSC. All rights reserved.
 */

public class Logcat {

    private class TAG {
        public static final String DEBUG = App.NAME + "-DEBUG";
        public static final String WARNING = App.NAME + "-WARNING";
        public static final String ERROR = App.NAME + "-ERROR";
    }

    public static void d(String s) {
        Log.d(TAG.DEBUG, simple(s));
    }

    public static void d(String tag, String s) {
        Log.d(tag, simple(s));
    }

    public static void e(Exception e) {
        if (isNotNull(e)) {
            Log.d(TAG.ERROR, e.getClass().getSimpleName() + ":" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void e(String e) {
        Log.e(TAG.ERROR, simple(e));
    }

    public static void e(Throwable t) {
        if (isNotNull(t)) {
            Log.e(TAG.ERROR, t.getClass().getSimpleName() + ":" + t.getMessage());
            t.printStackTrace();
        }
    }

    public static void w(String s) {
        Log.w(TAG.WARNING, simple(s));
    }

    public static void w(Exception e) {
        if (isNotNull(e)) {
            Log.w(TAG.WARNING, e.getClass().getSimpleName() + ":" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void w(Throwable t) {
        if (isNotNull(t)) {
            Log.w(TAG.WARNING, t.getClass().getSimpleName() + ":" + t.getMessage());
            t.printStackTrace();
        }
    }
}
