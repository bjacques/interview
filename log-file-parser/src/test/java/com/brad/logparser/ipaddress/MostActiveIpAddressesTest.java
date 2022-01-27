package com.brad.logparser.ipaddress;

import com.brad.logparser.testing.TestFiles;
import org.junit.Test;

import java.nio.file.Path;
import java.util.List;

import static com.brad.logparser.ipaddress.MostActiveIpAddressReport.mostActiveIpAddressesFromFile;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

public class MostActiveIpAddressesTest {

    @Test
    public void shouldReturnTop3MostActiveIpAddress_givenInterviewSampleFile() throws Exception {
        Path path = TestFiles.getPathFor("programming-task-example-data.log");
        List<IpAddressCount> top3 = mostActiveIpAddressesFromFile(path);
        assertThat(top3, contains(
                IpAddressCount.of("168.41.191.40", 4L),
                IpAddressCount.of("177.71.128.21", 3L),
                IpAddressCount.of("72.44.32.10", 3L)
        ));
    }
}
