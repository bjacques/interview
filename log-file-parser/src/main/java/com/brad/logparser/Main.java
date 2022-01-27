package com.brad.logparser;

import com.brad.logparser.ipaddress.MostActiveIpAddressReport;
import com.brad.logparser.ipaddress.UniqueIpAddressReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        logger.info("Starting...");
        //todo: validate args and provide usage
        var filepath = Path.of(args[0]);
        UniqueIpAddressReport.uniqueIpAddressesFromFile(filepath);
        MostActiveIpAddressReport.mostActiveIpAddressesFromFile(filepath);
        logger.info("Done");
    }
}
