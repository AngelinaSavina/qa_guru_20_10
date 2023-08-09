package guru.qa.homeTask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZIPFilesReadPDFTest {

    @Test
    @DisplayName("Реализовать чтение и проверку содержимого PDF файла из архива")
    void zipPdfTest() throws Exception {
        try (ZipFile zipFile = new ZipFile("src/test/resources/qa_guru_20_10_HomeTask/PDF_example.pdf.zip")) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                Assertions.assertEquals("PDF_example.pdf", entry.getName());
                System.out.println("тест пройден");
                return;
            }
        }
    }
}