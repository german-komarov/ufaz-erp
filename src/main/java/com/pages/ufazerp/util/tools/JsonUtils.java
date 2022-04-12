package com.pages.ufazerp.util.tools;

import java.util.Collections;
import java.util.Map;

public class JsonUtils {
    public static Map<String, Object> json(String key, Object value) {
        return Collections.singletonMap(key, value);
    }

    public static Map<String, Object> message(Object value) {
        return json("message", value);
    }
}
