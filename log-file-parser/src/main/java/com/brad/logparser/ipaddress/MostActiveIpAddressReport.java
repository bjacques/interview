package com.brad.logparser.ipaddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class MostActiveIpAddressReport {
    private static final Logger log = LoggerFactory.getLogger(MostActiveIpAddressReport.class);

    /**
     * Sorts using the highest count (value), and if the count is tied, by the ip address (key)
     */
    private static Comparator<? super Map.Entry<String, Long>> byHighestCount = (e1, e2) -> {
        int countCompare = e2.getValue().compareTo(e1.getValue());
        return countCompare != 0 ? countCompare : e1.getKey().compareTo(e2.getKey());
    };

    public static List<IpAddressCount> mostActiveIpAddressesFromFile(Path path) throws IOException {
        log.info("Reporting Most Active IP Addresses from {}", path);
        final int topCount = 3;
        List<IpAddressCount> top;
        try (var lines = Files.lines(path)) {
            Map<String, Long> ips = lines
                    .parallel()
                    .map(IpAddressLineParser::extractIpAddress)
                    .filter(ip -> !ip.isBlank())
                    .collect(groupingByConcurrent(identity(), counting()));
            log.info("Collected {} map entries", ips.size());
            top = ips.entrySet().stream()
                    .sorted(byHighestCount)
                    .limit(topCount)
                    .map(IpAddressCount::of)
                    .collect(toList());
            log.info("Top {} most active {}", topCount, top);
        }
        log.info("Reporting Most Active IP Addresses Completed");
        return top;
    }
}
