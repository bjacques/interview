package com.brad.logparser.extract;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LineParserTest {

    @Test
    public void returnIpAddress_givenIpAddressValid() {
        var ip = LineParser.extractIpAddress("127.0.0.1");
        assertThat(ip, is("127.0.0.1"));
    }

    @Test
    public void returnEmptyString_givenIpAddressIsNotValid_doubleZero() {
        var invalidIp = LineParser.extractIpAddress("50.112.00.11");
        assertThat(invalidIp, is(""));
    }

    @Test
    public void returnEmptyString_givenIpAddressIsNotValid_biggerThan255() {
        var invalidIp = LineParser.extractIpAddress("300.0.0.1");
        assertThat(invalidIp, is(""));
    }

    @Test
    public void returnIpAddress_fromFrontOfString() {
        var ip = LineParser.extractIpAddress("192.168.0.1 abc");
        assertThat(ip, is("192.168.0.1"));
    }

    @Test
    public void returnEmptyString_givenIpAddressIsNotStartOfLine() {
        var ip = LineParser.extractIpAddress("abc 192.168.0.1");
        assertThat(ip, is(""));
    }

    @Test
    public void returnEmptyString_givenNull() {
        var ip = LineParser.extractIpAddress(null);
        assertThat(ip, is(""));
    }
}