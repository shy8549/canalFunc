package com.workflow.functions.service;
import org.apache.spark.sql.api.java.UDF1;
/**
 * @author: create by suhy
 * @version: v1.0
 * @description: Ip2Long
 * @className: Ip2Long
 * @date:2021/8/30 11:54
 */
public class Ip2Long implements UDF1<String, Long>{
    private static final long serialVersionUID = 5801470612476075609L;


    public Long call(String input)
    {
        if ((input.isEmpty()) || (null == input)) {
            return null;
        }
        return IpTransform.ipToLong(input.toString());
    }

}
