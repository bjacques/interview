package com.brad.logparser.url;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlLineParser {
    /*
    todo: clarify requirements
        are we only interested in GET and POST?
        do we include query parameters?
     */
    private static Pattern urlAnywhereOnLine = Pattern.compile("((GET|POST) )(\\S+)(\\s|$)");

    /**
     *
     * @param line
     * @return URL if found, otherwise empty string
     */
    public static String extractUrl(String line) {
        if(line == null) return "";
        Matcher m = urlAnywhereOnLine.matcher(line);
        if (m.find()) {
            return m.group(3);
        }
        return "";
    }
}
