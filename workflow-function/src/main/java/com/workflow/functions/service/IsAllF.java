package com.workflow.functions.service;

import org.apache.spark.sql.api.java.UDF2;

import java.util.Objects;

/**
 * @Author: lsj
 * @Date: 2022/6/6
 * 根据十进制对应的16进制是否全F
 */
public class IsAllF implements UDF2<String, String, Integer> {

    /**
     * 单字节：0xff
     */
    private static final String SINGLE_BYTE = "2";
    private static final String SINGLE_BYTE_VALUE = "255";
    /**
     * 2字节：0xffff
     */
    private static final String TWO_BYTES = "4";
    private static final String TWO_BYTES_VALUE = "65535";
    /**
     * 4字节：0xffffffff
     */
    private static final String FOUR_BYTES = "8";
    private static final String FOUR_BYTES_VALUE = "4294967295";
    /**
     * 8字节：0xffffffffffffffff
     */
    private static final String EIGHT_BYTES = "16";
    private static final String EIGHT_BYTES_VALUE = "18446744073709551615";

    /**
     *
     * @param number 10进制数字
     * @param bit 16进制位数
     * @return
     */
    public Integer isAllF(String number, String bit) {
        if (SINGLE_BYTE.equals(bit) && SINGLE_BYTE_VALUE.equals(number)) {
            return 1;
        } else if (TWO_BYTES.equals(bit) && TWO_BYTES_VALUE.equals(number)) {
            return 1;
        } else if (FOUR_BYTES.equals(bit) && FOUR_BYTES_VALUE.equals(number)) {
            return 1;
        } else if (EIGHT_BYTES.equals(bit) && EIGHT_BYTES_VALUE.equals(number)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Integer call(String number, String bit) throws Exception {
        if (Objects.isNull(number) || Objects.isNull(bit)) {
            return null;
        }
        return isAllF(number, bit);
    }

    public static void main(String[] args) throws Exception {
        IsAllF isAllF = new IsAllF();
        System.out.println(isAllF.call("184467440737095516151", "16"));
    }
}
