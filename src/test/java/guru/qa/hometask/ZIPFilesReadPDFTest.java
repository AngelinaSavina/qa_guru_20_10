package guru.qa.hometask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZIPFilesReadPDFTest {

    @Test
    @DisplayName("Реализовать чтение и проверку содержимого PDF файла из архива")
    void zipPdfTest() throws Exception {
        try (ZipFile zipFile = new ZipFile("src/test/resources/qa_guru_20_10_HomeTask.zip")) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String fileName = entry.getName();
                if (fileName.endsWith(".pdf")) {
                    String actualFileName = new File(fileName).getName();
                    Assertions.assertEquals("book.pdf", actualFileName);
                    //Assertions.assertEquals("PDF_example.pdf", entry.getName());
                    break;
                }
            }
        }
    }
}