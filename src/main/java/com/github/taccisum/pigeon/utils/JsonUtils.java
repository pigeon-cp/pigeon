package com.github.taccisum.pigeon.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.1
 */
public abstract class JsonUtils {
    private static ObjectMapper objectMapper;

    public static void setObjectMapper(ObjectMapper objectMapper) {
        JsonUtils.objectMapper = objectMapper;
    }

    /**
     * 将对象转换成 json 字符串
     */
    public static String stringify(Object obj) {
        return JsonUtils.stringify(obj, null);
    }

    /**
     * 将对象转换成 json 字符串
     *
     * @param obj 要转换的对象
     * @param def 转换失败时的默认值
     */
    public static String stringify(Object obj, String def) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            if (def == null) {
                if (obj instanceof Array || obj instanceof Collection) {
                    return "[]";
                } else {
                    return "{}";
                }
            }
            return def;
        }
    }
}
