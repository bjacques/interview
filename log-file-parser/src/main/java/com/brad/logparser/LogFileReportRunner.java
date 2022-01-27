package com.brad.logparser;

import com.brad.logparser.extract.LineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class LogFileReportRunner {
    private static final Logger log = LoggerFactory.getLogger(LogFileReportRunner.class);

    public static Set<String> uniqueIpAddressesFromFile(Path path) throws IOException {
        log.info("Reporting Unique IP Addresses from {}", path);
        Set<String> ips;
        try (var lines = Files.lines(path)) {
            ips = lines
//                    .parallel()   // alternative: https://stackoverflow.com/questions/21163108/custom-thread-pool-in-java-8-parallel-stream
                    .map(LineParser::extractIpAddress)
                    .filter(ip -> !ip.isBlank())
                    .collect(toSet());
            log.info("Found {}", ips.size());
            ips.forEach(log::info);
        }
        log.info("Reporting Unique IP Addresses Completed");
        return ips;
    }
}
