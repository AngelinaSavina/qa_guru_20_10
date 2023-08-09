package guru.qa.homeTask;

import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Assertions;
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
            try (ZipFile zipFile = new ZipFile("src/test/resources/qa_guru_20_10_HomeTask/XLSX_example.zip")) {
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                 while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    InputStream stream = zipFile.getInputStream(entry);
                    Assertions.assertEquals("XLSX_example.xlsx", entry.getName());
                    XLS xls = new XLS(stream);
                    System.out.println("39");

                    assertThat(
                        xls.excel.getSheetAt(0)
                                 .getRow(1)
                                 .getCell(1)
                                 .getStringCellValue())
                                 .contains("TEST");
                    return;
       }
            }
        }
    }
