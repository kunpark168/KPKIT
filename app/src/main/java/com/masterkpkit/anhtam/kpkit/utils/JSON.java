package com.masterkpkit.anhtam.kpkit.utils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author : longvd
 * @since : 1/6/17
 * Copyright (c) 2017 NTEX JSC. All rights reserved.
 */

public class JSON {

    private static final Gson instance = new Gson();

    private JSON() {

    }

    public static String encode(Object obj) {
        return instance.toJson(obj);
    }

    public static <T> T decode(String json, Class<T> tClass) {
        try {
            return instance.fromJson(json, tClass);
        } catch (Exception e) {
            Logcat.e(e);
        }
        return null;
    }

    public static String toJsonArray(String jsonTree) throws JsonTreeSyntaxError {
        try {
            Map<String, Object> ripMyStack = toMap(new JSONObject(jsonTree));
            List<Object> ripMyStackAgain = new ArrayList<>();
            for (Map.Entry<String, Object> myPreciousMemory : ripMyStack.entrySet()) {
                ripMyStackAgain.add(myPreciousMemory.getValue());
            }
            return new Gson().toJson(ripMyStackAgain.toArray());
        } catch (JSONException e) {
            Logcat.e(e);
        }
        throw new JsonTreeSyntaxError();
    }

    public static class JsonTreeSyntaxError extends Exception {
        private JsonTreeSyntaxError() {
            super("This is not a JSON tree");
        }
    }

    private static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<>();
        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }
    private static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
}
