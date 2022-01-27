package com.brad.logparser.ipaddress;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpAddressLineParser {
    /*
    private static String naive = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
    https://stackoverflow.com/questions/5284147/validating-ipv4-addresses-with-regexp
     */
    private static String strict = "^(?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])\\.){3}(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])";
    private static Pattern ipAddressAtStartOfLine = Pattern.compile(strict);

    /**
     *
     * @param line
     * @return ip address if found, otherwise empty string
     */
    public static String extractIpAddress(String line) {
        if(line == null) return "";
        Matcher m = ipAddressAtStartOfLine.matcher(line);
        if (m.find()) {
            return m.group(0);
        }
        return "";
    }
}
