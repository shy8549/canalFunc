package com.workflow.functions.service;

import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: Ip2Long
 * @className: IpTransform
 * @date:2021/8/30 11:19
 */
public class IpTransform {
    public static Long ipToLong(String ip) {
        String[] ips = StringUtils.split(ip, ".");
        long result = 0L;
        try {
            result = (Long.parseLong(ips[0]) << 24) + (Long.parseLong(ips[1]) << 16) + (Long.parseLong(ips[2]) << 8) + Long.parseLong(ips[3]);
        } catch (Exception e) {
            throw new RuntimeException("Ip address fault! IP:" + ip, e);
        }
        return Long.valueOf(result);
    }

    public static String longToIp(long ip, String type) {
        String ipRes = (ip >> 24 & 0xFF) + "." + (ip >> 16 & 0xFF) + "." + (ip >> 8 & 0xFF) + "." + (ip & 0xFF);

        if ("B".equals(type)) {
            return StringUtils.split(ipRes, ".")[0] + "." + StringUtils.split(ipRes, ".")[1] + ".0.0";
        }
        if ("C".equals(type)) {
            return StringUtils.split(ipRes, ".")[0] + "." + StringUtils.split(ipRes, ".")[1] + "." + StringUtils.split(ipRes, ".")[2] + ".0";
        }

        return ipRes;
    }

    public static long mlteIpToDecimalism(String ip) {
        Pattern pattern = Pattern.compile("^f*");
        String ipT = pattern.matcher(ip.toLowerCase()).replaceAll("");
        if (ipT.length() == 8) {
            return Long.parseLong(ipT, 16);
        }
        return -1L;
    }

}

