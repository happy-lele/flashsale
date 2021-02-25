
package com.le.flashsale.common.gson;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * Date 2020/11/17 9:43 下午
 * Author le
 */
public class GsonUtils {
    /**
     * gson
     */
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateAdapter())
            .registerTypeAdapter(Timestamp.class, new TimestampAdapter()).create();

    /**
     * 从对象转换成json字符串
     *
     * @param obj 待转换对象
     * @return String
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * 将json字符串转换为指定类型的对象
     *
     * @param <T>      对象类型泛型
     * @param json     json字符串
     * @param classOfT 对象类型
     * @return 转换后的对象
     * @throws JsonParseException jsonparse异常
     */
    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonParseException {
        return gson.fromJson(json, classOfT);

    }

    /**
     * 将json字符串转换为指定类型的对象
     *
     * @param json    json字符串
     * @param typeOfT 对象类型
     * @return 转换后的对象
     * @throws JsonSyntaxException jsonparse异常
     */
    public static Object fromJson(String json, Type typeOfT) throws JsonSyntaxException {
        return gson.fromJson(json, typeOfT);
    }

    /**
     * 将json字符串转换为指定类型的对象
     *
     * @param json  json字符串
     * @param token 对象类型
     * @return 转换后的对象
     * @throws JsonSyntaxException jsonparse异常
     */
    public static <T> T fromJson(String json, TypeToken token) throws JsonSyntaxException {
        return gson.fromJson(json, token.getType());
    }

    /**
     * 把一个简单键值对的json转换成map的数据结构，只支持key和value都是String的
     *
     * @param json json
     * @return 转化后的map对象
     * @throws JsonParseException
     */
    public static Map<String, String> fromJson(String json) throws JsonParseException {
        return gson.fromJson(json, new TypeToken<Map<String, String>>() {
            private static final long serialVersionUID = 1L;
        }.getType());
    }

    /**
     * 把一个简单键值对的json转换成map的数据结构，只支持key和value都是String的
     *
     * @param json json
     * @return 转化后的map对象
     * @throws JsonParseException
     */
    public static Map<String, Object> fromJson2Obj(String json) throws JsonParseException {
        return gson.fromJson(json, new TypeToken<Map<String, Object>>() {
            private static final long serialVersionUID = 1L;
        }.getType());
    }

    /**
     * 把一个简单键值对的json转换成map的数据结构，只支持key和value都是String的
     *
     * @param json json
     * @return 转化后的map对象
     * @throws JsonParseException
     */
    public static List<Map<String, String>> fromJson2List(String json) throws JsonParseException {
        return gson.fromJson(json, new TypeToken<List<Map<String, String>>>() {
            private static final long serialVersionUID = 1L;
        }.getType());
    }

    public static boolean isBadJson(String json) {
        return !isGoodJson(json);
    }

    /**
     * 判断一个字符串是否为一个正常的json串
     *
     * @param json
     * @return
     */
    public static boolean isGoodJson(String json) {
        if (StringUtils.isBlank(json)) {
            return false;
        }
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            return false;
        }
    }

    /**
     * 把一个简单键值对的json转换成map的数据结构，
     *
     * @param json json
     * @return 转化后的map对象
     * @throws JsonParseException
     */
    public static List<Map<String, Object>> fromJson2CompList(String json) throws JsonParseException {
        return gson.fromJson(json, new TypeToken<List<Map<String, Object>>>() {
            private static final long serialVersionUID = 1L;
        }.getType());
    }

}
