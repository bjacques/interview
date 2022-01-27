package com.brad.logparser.url;

import com.brad.logparser.ipaddress.IpAddressCount;
import com.brad.logparser.ipaddress.IpAddressLineParser;
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

public class MostVisitedUrlsReport {
    private static final Logger log = LoggerFactory.getLogger(MostVisitedUrlsReport.class);

    /**
     * Sorts using the highest count (value), and if the count is tied, by the url (key)
     */
    private static Comparator<? super Map.Entry<String, Long>> byHighestCount = (e1, e2) -> {
        int countCompare = e2.getValue().compareTo(e1.getValue());
        return countCompare != 0 ? countCompare : e1.getKey().compareTo(e2.getKey());
    };

    public static List<UrlCount> mostVisitedUrlsFromFile(Path path) throws IOException {
        log.info("Reporting Most Visited URLs from {}", path);
        final int topCount = 3;
        List<UrlCount> top;
        try (var lines = Files.lines(path)) {
            Map<String, Long> urls = lines
                    .parallel()
                    .map(UrlLineParser::extractUrl)
                    .filter(ip -> !ip.isBlank())
                    .collect(groupingByConcurrent(identity(), counting()));
            log.info("Collected {} map entries", urls.size());
            top = urls.entrySet().stream()
                    .sorted(byHighestCount)
                    .limit(topCount)
                    .map(UrlCount::of)
                    .collect(toList());
            log.info("Top {} most visited {}", topCount, top);
        }
        log.info("Reporting Most Visited URLs Completed");
        return top;
    }
}
