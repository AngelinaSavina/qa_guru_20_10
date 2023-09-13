package guru.qa.hometask;
import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZIPFilesReadPDFTest {

    @Test
    @DisplayName("Реализовать чтение и проверку содержимого PDF файла из архива")
    void zipPdfTest() throws Exception {
        try (ZipFile zipFile = new ZipFile("src/test/resources/qa_guru_20_10_HomeTask.zip")) {
            ZipEntry entry = zipFile.getEntry("qa_guru_20_10_HomeTask/book.pdf");
            InputStream stream = zipFile.getInputStream(entry);
            PDF pdfFile = new PDF(stream);
            Assertions.assertTrue(pdfFile.text.contains("Work where you want"));
        }
    }
}