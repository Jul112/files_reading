package tests;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.codeborne.pdftest.PDF.containsText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.ReadFiles.*;

public class CheckFilesContentTests {
    @Test
    void xlsxFileTest() {
        String xlsxFilePath = "./src/test/resources/files/xlsxFile.xlsx";
        String expectedText = "Xlsx for test";
        String actualText = readXlsxFromFilePath(xlsxFilePath);

        assertThat(actualText, containsString(expectedText));
    }

    @Test
    void docxFileTest() throws Exception {
        String docxFilePath = "./src/test/resources/files/docxFile.docx";
        String expectedText = "Docx for test";
        String actualText = readDocxFromFilePath(docxFilePath);

        assertThat(actualText, containsString(expectedText));
    }

    @Test
    void pdfFileTest() throws IOException {
        String pdfFilePath = "./src/test/resources/files/pdfFile.pdf";
        String expectedText = "Pdf for test";
        PDF actualText = readPdfFromFilePath(pdfFilePath);

        assertThat(actualText, containsText(expectedText));
    }
}