package guru.qa.HomeTask;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParserBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class ZIPFilesReadTest {
    ClassLoader classLoader = ZIPFilesReadTest.class.getClassLoader();

    @Test
    @DisplayName("Реализовать чтение и проверку содержимого каждого файла из архива")
    void zipTest() throws Exception {
        try (InputStream is = classLoader.getResourceAsStream("qa_guru_20_10_ZIP_HomeTask.zip");) {
            assert is != null;
            ZipInputStream zis = new ZipInputStream(is);
            ZipEntry entry;
            entry = zis.getNextEntry();
            while (entry != null) {
                if (entry.getName().contains("PDF_example")) {
                    System.out.println("1");
                    PDF pdf = new PDF(zis);
                    assertThat(pdf.text).contains("Work where you want");
                } else if (entry.getName().contains("XLSX")) {
                    XLS xls = new XLS(zis);
                    assertThat(
                            xls.excel.getSheetAt(0)
                                    .getRow(1)
                                    .getCell(1)
                                    .getStringCellValue())
                            .contains("TEST");
                } else if (entry.getName().contains("CSV")) {
                    InputStreamReader inputReader = new InputStreamReader(zis, UTF_8);
                    CSVParser csvParser = new CSVParserBuilder()
                            .withSeparator(';')
                            .build();
                    CSVReader csvReader = new CSVReaderBuilder(inputReader)
                            .withCSVParser(csvParser)
                            .build();
                    //CSVReader csvReader = new CSVReader(new InputStreamReader(zis, UTF_8));
                    List<String[]> csv = csvReader.readAll();
                    assertThat(csv).contains(
                            new String[]{"test", "значение"},
                            new String[]{"test 1", "значение 1"},
                            new String[]{"test 2", "значение 2"});
                }

                ZipEntry nextEntry = zis.getNextEntry();

                if (nextEntry.getName().contains("__MACOSX")) {
                    nextEntry = zis.getNextEntry();
                }
                entry = nextEntry;
            }
        }
    }
}