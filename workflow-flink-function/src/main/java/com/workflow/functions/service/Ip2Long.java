package com.workflow.functions.service;

import org.apache.flink.table.functions.ScalarFunction;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description:
 * @className: Ip2Long
 * @date:2022/1/6 17:03
 */
public  class Ip2Long extends ScalarFunction  {

    public Long eval(String input){
        if ((input.isEmpty()) || (null == input)) {
            return null;
        }
        return IpTransform.ipToLong(input.toString());
    }
}
