package com.workflow.functions.service;

import org.apache.spark.sql.api.java.UDF1;
import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description:
 * @className: Str2Crc64
 * @date:2022/4/6 9:58
 */
public class Str2Crc64 implements UDF1<String, String>  {

    private static final long serialVersionUID = 4792214220027609718L;

    public static String crc64(String content) {
        String crc64 = null;
        if (content != null) {
            String md5 = DigestUtils.md5Hex(content);
            String convStr = md5.substring(0, 16);
            BigInteger tmp = new BigInteger(convStr, 16);
            crc64 = tmp.toString();
        }
        return crc64;
    }

    @Override
    public String call(String input) {
        if(input.isEmpty()){
            return null;
        }
        return crc64(input);
    }

    public static void main(String[] args) {
        Str2Crc64 crc64 = new Str2Crc64();
        System.out.println(crc64.call(""));
    }
}
