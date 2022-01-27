package com.brad.logparser.ipaddress;

import java.util.Map;
import java.util.Objects;

public final class IpAddressCount {
    private final String address;
    private final Long count;

    public IpAddressCount(String key, Long value) {
        this.address = key;
        this.count = value;
    }

    public static IpAddressCount of(Map.Entry<String,Long> entry) {
        return new IpAddressCount(entry.getKey(), entry.getValue());
    }

    public static IpAddressCount of(String address, Long count) {
        return new IpAddressCount(address, count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpAddressCount that = (IpAddressCount) o;
        return Objects.equals(address, that.address) && Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, count);
    }

    @Override
    public String toString() {
        return "IpAddressCount{" +
                "name='" + address + '\'' +
                ", count=" + count +
                '}';
    }
}
