package com.brad.logparser.end2end;

import com.brad.logparser.testing.TestFiles;
import com.brad.logparser.url.UrlCount;
import org.junit.Test;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

import static com.brad.logparser.url.MostVisitedUrlsReport.mostVisitedUrlsFromFile;
import static java.util.Collections.EMPTY_LIST;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

public class MostVisitedUrlsTest {

    @Test
    public void shouldReturnTop3MostVisitedUrls_givenInterviewSampleFile() throws Exception {
        Path path = TestFiles.getPathFor("programming-task-example-data.log");
        List<UrlCount> top3Urls = mostVisitedUrlsFromFile(path);
        assertThat(top3Urls, contains(
                UrlCount.of("/docs/manage-websites/", 2L),
                UrlCount.of("/", 1L),
                UrlCount.of("/asset.css", 1L)
                ));
    }

    @Test
    public void shouldReturnTwoCounts_forOneUrl_givenItAppearsTwiceInFile() throws Exception {
        Path path = TestFiles.getPathFor("urls/two-the-same.log");
        List<UrlCount> top3Urls = mostVisitedUrlsFromFile(path);
        assertThat(top3Urls, contains(
                UrlCount.of("/intranet-analytics/", 2L),
                UrlCount.of("/foo", 1L)
        ));
    }

    @Test
    public void shouldReturnEmptySet_givenEmptyFile() throws Exception {
        Path path = TestFiles.getPathFor("empty.log");
        List<UrlCount> top3Urls = mostVisitedUrlsFromFile(path);
        assertThat(top3Urls, is(EMPTY_LIST));
    }

    @Test
    public void shouldReturnEmptySet_givenFileDoesNotContainAUrl() throws Exception {
        Path path = TestFiles.getPathFor("urls/no-url.log");
        List<UrlCount> top3Urls = mostVisitedUrlsFromFile(path);
        assertThat(top3Urls, is(EMPTY_LIST));
    }

    @Test
    public void shouldReturnOneIpAddress_ignoringBlankLines_givenFileContainsOneIpAddress() throws Exception {
        Path path = TestFiles.getPathFor("blank-lines.log");
        List<UrlCount> top3Urls = mostVisitedUrlsFromFile(path);
        assertThat(top3Urls, contains(UrlCount.of("/intranet-analytics/", 1L)));
    }

    @Test(expected = NoSuchFileException.class)
    public void shouldThrowException_givenFileDoesNotExist() throws Exception {
        Path path = TestFiles.getPathFor("xxx");
        mostVisitedUrlsFromFile(path);
    }
}
