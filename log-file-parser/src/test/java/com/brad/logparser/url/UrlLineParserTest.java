package com.brad.logparser.url;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UrlLineParserTest {

    @Test
    public void returnUrl_givenGetRequestUrl() {
        var url = UrlLineParser.extractUrl("GET http://acme.com/products");
        assertThat(url, is("http://acme.com/products"));
    }

    @Test
    public void returnUrl_givenPostRequestUrl() {
        var url = UrlLineParser.extractUrl("POST http://acme.com/products");
        assertThat(url, is("http://acme.com/products"));
    }

    @Test
    public void returnUrl_givenGetRequestUrlWithQueryParameters() {
        var url = UrlLineParser.extractUrl("GET http://acme.com/products?a=b");
        assertThat(url, is("http://acme.com/products?a=b"));
    }

    @Test
    public void returnUrl_givenHttpsRequest() {
        var url = UrlLineParser.extractUrl("GET https://acme.com/products");
        assertThat(url, is("https://acme.com/products"));
    }

    @Test
    public void returnEmptyString_givenNull() {
        var url = UrlLineParser.extractUrl(null);
        assertThat(url, is(""));
    }

    @Test
    public void returnUrl_givenGetRequestUrl_inMiddleOfLine() {
        var url = UrlLineParser.extractUrl("abc GET http://acme.com/products abc");
        assertThat(url, is("http://acme.com/products"));
    }

}