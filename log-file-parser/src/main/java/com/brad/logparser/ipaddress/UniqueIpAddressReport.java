package com.brad.logparser.ipaddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class UniqueIpAddressReport {
    private static final Logger log = LoggerFactory.getLogger(UniqueIpAddressReport.class);

    public static Set<String> uniqueIpAddressesFromFile(Path path) throws IOException {
        log.info("Reporting Unique IP Addresses from {}", path);
        Set<String> ips;
        try (var lines = Files.lines(path)) {
            ips = lines
//                    .parallel()   // alternative: https://stackoverflow.com/questions/21163108/custom-thread-pool-in-java-8-parallel-stream
                    .map(IpAddressLineParser::extractIpAddress)
                    .filter(ip -> !ip.isBlank())
                    .collect(toSet());
            log.info("Found {}", ips.size());
            ips.forEach(log::info);
        }
        log.info("Reporting Unique IP Addresses Completed");
        return ips;
    }
}