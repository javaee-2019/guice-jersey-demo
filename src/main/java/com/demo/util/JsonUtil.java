package com.demo.util;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Json工具类
 *
 */
public class JsonUtil {

    private JsonUtil() {
    }

    /**
     * 对象转Json字符串
     *
     * @param object 对象
     * @return Json字符串
     */
    public static String obj2Json(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * Json字符串转对象
     *
     * @param json   Json字符串
     * @param tClass 对象类型
     * @return 对象
     */
    public static <T> T json2Obj(String json, Class<T> tClass) {
        return JSON.parseObject(json, tClass);
    }

    /**
     * Json字符串转Object对象
     *
     * @param json Json字符串
     * @return Object对象
     */
    public static Object json2Obj(String json) {
        return JSON.parse(json);
    }

    /**
     * JSON字符串转List数组
     * @param json json字符串
     * @param tClass 泛型类型
     * @param <T> 泛型类
     * @return list数组
     */
    public static <T> List<T> json2List(String json, Class<T> tClass) {
        return JSON.parseArray(json, tClass);
    }

    /**
     * 将流转换为对象
     *
     * @param is     输入流
     * @param tClass 对象类
     * @return 对象
     * @throws IOException 读取异常
     */
    public static <T> T inputStream2Object(InputStream is, Class<T> tClass) throws IOException {
        return JSON.parseObject(is, tClass);
    }

    public static Map<String, Object> json2Map(String json) {
        return json2Obj(json, Map.class);
    }

    public static Map<String, String> json2StringMap(String json) {
        return json2Obj(json, Map.class);
    }
}
