package com.demo.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Map.Entry;

public class SignatureGenerator {
    public SignatureGenerator() {
    }

    public static String generate(String urlResourcePart, Map<String, String> params, String secretKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        List<Entry<String, String>> parameters = new LinkedList(params.entrySet());
        Collections.sort(parameters, new Comparator<Entry<String, String>>() {
            public int compare(Entry<String, String> o1, Entry<String, String> o2) {
                return ((String)o1.getKey()).compareTo((String)o2.getKey());
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append(urlResourcePart).append("_");
        Iterator i$ = parameters.iterator();

        while(i$.hasNext()) {
            Entry<String, String> param = (Entry)i$.next();
            sb.append((String)param.getKey()).append("=").append((String)param.getValue()).append("_");
        }

        sb.append(secretKey);
        String baseString = URLEncoder.encode(sb.toString(), "UTF-8");
        return MD5Util.md5(baseString);
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, String> params = new HashMap();
        params.put("paramB", "valueB");
        params.put("paramA", "valueA");
        params.put("paramC", "valueC");
        params.put("appkey", "1234567890");
        String urlResourcePart = "user/user/getUserInfo";
        String sign = generate(urlResourcePart, params, "abcdefghijklmnopqrstuvwxyz123456");
        System.out.println(sign);
    }
}

