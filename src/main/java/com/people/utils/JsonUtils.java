package com.people.utils;

import com.google.gson.*;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static <T> String toJson(T obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static <T> String toJsonWithoutUnicode(T obj) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(obj);
    }

    public static <T> String toJsonWithNull(T obj) {
        Gson gson = new GsonBuilder().serializeNulls().create();
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
    public static JsonObject parse(String json) {
        return new JsonParser().parse(json).getAsJsonObject();
    }
    public static List<String> getKeyChain(String key) {
        String[] splitKey = key.split("[.\\[]");
        List<String> keys = new ArrayList<>();
        for(String singleKey : splitKey) {
            if(singleKey.matches(".*]$")) {
                keys.add("[" + singleKey);
            } else {
                keys.add(singleKey);
            }
        }
        return keys;
    }
    public static String getValue(String json, String key) {
        if(StringUtils.isEmpty(json)) return "";

        return getValue(parse(json), key);
    }

    public static String getValue(JsonObject jsonObject, String key) {
        List<String> keyChain = getKeyChain(key);

        JsonElement elem = jsonObject.deepCopy();
        for(String singleKey : keyChain) {
            if(singleKey.matches("\\[.*]$")) {
                int len = singleKey.length();
                int index = Integer.parseInt(singleKey.substring(1, len -1));
                elem = elem.getAsJsonArray().get(index);
            } else {
                elem = elem.getAsJsonObject().get(singleKey);
                if(elem == null || elem.isJsonNull()) return "";
                if(elem.isJsonPrimitive()) return elem.getAsJsonPrimitive().getAsString();
                if(elem.isJsonArray()) {
                    elem = elem.getAsJsonArray();
                } else {
                    elem = elem.getAsJsonObject();
                }
            }
        }
        return elem.toString();

    }
}
