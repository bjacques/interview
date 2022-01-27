package com.brad.logparser.ipaddress;

import com.brad.logparser.testing.TestFiles;
import org.junit.Test;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Set;

import static com.brad.logparser.ipaddress.UniqueIpAddressReport.uniqueIpAddressesFromFile;
import static java.util.Collections.EMPTY_SET;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class UniqueIpAddressesTest {

    @Test
    public void shouldReturnEightUniqueValidIpAddresses_givenInterviewSampleFile() throws Exception {
        Path path = TestFiles.getPathFor("programming-task-example-data.log");
        Set<String> uniqueIps = uniqueIpAddressesFromFile(path);
        assertThat(uniqueIps, containsInAnyOrder(
                "168.41.191.34",
                        "168.41.191.41",
                        "168.41.191.43",
                        "72.44.32.11",
                        "177.71.128.21",
                        "168.41.191.40",
                        "168.41.191.9",
                        "72.44.32.10"));
    }

    @Test
    public void shouldReturnTwoUniqueIpAddresses_givenThreeIpAddressesWhereTwoAreTheSame() throws Exception {
        Path path = TestFiles.getPathFor("ipaddress/two-unique.log");
        Set<String> uniqueIps = uniqueIpAddressesFromFile(path);
        assertThat(uniqueIps, containsInAnyOrder(
                "177.71.128.21",
                "176.60.0.8"
        ));
    }

    @Test
    public void shouldReturnEmptySet_givenEmptyFile() throws Exception {
        Path path = TestFiles.getPathFor("empty.log");
        Set<String> uniqueIps = uniqueIpAddressesFromFile(path);
        assertThat(uniqueIps, is(EMPTY_SET));
    }

    @Test
    public void shouldReturnEmptySet_givenFileDoesNotContainAnIpAddress() throws Exception {
        Path path = TestFiles.getPathFor("ipaddress/no-ip-address.log");
        Set<String> uniqueIps = uniqueIpAddressesFromFile(path);
        assertThat(uniqueIps, is(EMPTY_SET));
    }

    @Test
    public void shouldReturnOneIpAddress_ignoringBlankLines_givenFileContainsOneIpAddress() throws Exception {
        Path path = TestFiles.getPathFor("blank-lines.log");
        Set<String> uniqueIps = uniqueIpAddressesFromFile(path);
        assertThat(uniqueIps, containsInAnyOrder("177.71.128.21"));
    }

    @Test(expected = NoSuchFileException.class)
    public void shouldThrowException_givenFileDoesNotExist() throws Exception {
        Path path = TestFiles.getPathFor("xxx");
        uniqueIpAddressesFromFile(path);
    }
}
