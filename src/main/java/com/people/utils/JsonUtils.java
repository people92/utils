package com.people.utils;

import com.google.gson.*;

import java.lang.reflect.Type;

public class JsonUtils {

    public static <T> String toJson(T obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static <T> String toJsonWithoutUnicode(T obj) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, Type clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
    public static boolean isJsonArray(String json) {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(json, JsonElement.class);
        if(jsonElement.isJsonArray()) {
            return true;
        }
        return false;
    }

}
