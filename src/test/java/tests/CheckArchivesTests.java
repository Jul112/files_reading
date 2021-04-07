package tests;

import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.ReadArchives.unzipWithPassword;
import static utils.ReadFiles.readTextFromFilePath;

public class CheckArchivesTests {
    @Test
    void readFileFromZip() throws IOException, ZipException {
        String zipArchivePath = "./src/test/resources/archives/zipArchive.zip";
        String unzipDirPath = "./src/test/resources/archives/unpackedArchives";
        String zipPassword = "1234";
        String unzipFilePath = "./src/test/resources/archives/unpackedArchives/Text.txt";
        String expectedText = "Txt for test";

        unzipWithPassword(zipArchivePath, unzipDirPath, zipPassword);
        String actualData = readTextFromFilePath(unzipFilePath);
        assertThat(actualData, containsString(expectedText));
        }
}