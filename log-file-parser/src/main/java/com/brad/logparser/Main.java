package com.brad.logparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        logger.info("Starting...");
        // validate args
        var filepath = Path.of(args[0]);
        LogFileReportRunner.uniqueIpAddressesFromFile(filepath);
        logger.info("Done");
    }
}
