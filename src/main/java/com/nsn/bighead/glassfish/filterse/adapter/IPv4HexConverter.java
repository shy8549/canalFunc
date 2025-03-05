package com.nsn.bighead.glassfish.filterse.adapter;

import com.nsn.bighead.glassfish.filterse.handler.DataFilterAdapter;

public class IPv4HexConverter extends DataFilterAdapter {
    private static final long serialVersionUID = -1;

    @Override
    public String handleBuffer(String[] data, String todo, int columnX, String service, String extPars) {

        if (todo == null || todo.isEmpty()) {
            return "";
        }

        try {
            return hexToIpv4(todo);
        } catch (IllegalArgumentException e) {
            return todo; // 不能转换时，返回原值
        }
    }

    @Override
    public void init(String extPars) {

    }

    /**
     * 将IPv4地址转换为16进制格式（不带 0x）
     * @param ip IPv4地址（如 "100.89.3.226"）
     * @return 16进制格式字符串（如 "645903e2"）
     */
    public static String ipv4ToHex(String ip) {
        if (ip == null || ip.isEmpty()) {
            throw new IllegalArgumentException("Invalid IPv4 address");
        }

        String[] octets = ip.split("\\.");
        if (octets.length != 4) {
            throw new IllegalArgumentException("Invalid IPv4 address format");
        }

        StringBuilder hexString = new StringBuilder();
        for (String octet : octets) {
            int num = Integer.parseInt(octet);
            if (num < 0 || num > 255) {
                throw new IllegalArgumentException("IPv4 address octet out of range: " + octet);
            }
            hexString.append(String.format("%02x", num)); // 2位16进制，不足补0
        }

        return hexString.toString();
    }

    /**
     * 将16进制格式转换回IPv4地址
     * @param hex 16进制字符串（如 "645903e2"）
     * @return IPv4地址（如 "100.89.3.226"）
     */
    public static String hexToIpv4(String hex) {
        if (hex == null || hex.length() != 8) {
            throw new IllegalArgumentException("Invalid hex string length, must be 8 characters.");
        }

        StringBuilder ipString = new StringBuilder();
        for (int i = 0; i < 8; i += 2) {
            int octet = Integer.parseInt(hex.substring(i, i + 2), 16);
            ipString.append(octet);
            if (i < 6) {
                ipString.append(".");
            }
        }

        return ipString.toString();
    }


    public static void main(String[] args) {
        String[] data = new String[2];
        data[0] = "0";
        data[1] = "1";

        try {
            DataFilterAdapter adapter = (DataFilterAdapter) Class.forName("com.nsn.bighead.glassfish.filterse.adapter.IPv4HexConverter").newInstance();
            adapter.init("");
            String re = adapter.handleBuffer(data, "100.89.3.226", 0, "MLTE_S1U_HTTP", "");
            System.out.println(re);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
