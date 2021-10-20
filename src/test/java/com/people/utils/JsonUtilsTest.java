package com.people.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class JsonUtilsTest {

    @Test
    public void gsonUnicodeTest() {
        Map<String, String> map = new HashMap<>();
        map.put("unicode", "7456156==");

        System.out.println(JsonUtils.toJson(map));

        System.out.println(JsonUtils.toJsonWithoutUnicode(map));
    }

}
