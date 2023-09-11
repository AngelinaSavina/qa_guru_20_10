package guru.qa.hometask;

import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;

public class ZIPFilesReadXLSXTest {

    @Test
    @DisplayName("Реализовать чтение и проверку содержимого XLSX файла из архива")
    public void zipXlsxTest() throws Exception {
        try (ZipFile zipFile = new ZipFile("src/test/resources/qa_guru_20_10_HomeTask.zip")) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String fileName = entry.getName();
                if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
                    InputStream stream = zipFile.getInputStream(entry);
                    XLS xls = new XLS(stream);

                    assertThat(
                            xls.excel.getSheetAt(0)
                                    .getRow(1)
                                    .getCell(1)
                                    .getStringCellValue())
                            .contains("TEST");
                    break;
                }
            }
        }
    }
    }