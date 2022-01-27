package com.brad.logparser.url;

import java.util.Map;
import java.util.Objects;

public final class UrlCount {
    private final String url;
    private final Long count;

    public UrlCount(String key, Long value) {
        this.url = key;
        this.count = value;
    }

    public static UrlCount of(Map.Entry<String,Long> entry) {
        return new UrlCount(entry.getKey(), entry.getValue());
    }

    public static UrlCount of(String address, Long count) {
        return new UrlCount(address, count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlCount that = (UrlCount) o;
        return Objects.equals(url, that.url) && Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, count);
    }

    @Override
    public String toString() {
        return "IpAddressCount{" +
                "name='" + url + '\'' +
                ", count=" + count +
                '}';
    }
}
