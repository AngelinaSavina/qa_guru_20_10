package guru.qa.homeTask;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZIPFilesReadCSVTest {

    @Test
    @DisplayName("Реализовать чтение и проверку содержимого CSV файла из архива")
    void zipCsvTest() throws Exception {
        try (ZipFile zipFile = new ZipFile("src/test/resources/qa_guru_20_10_HomeTask/example_CSV.zip")) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                InputStream stream = zipFile.getInputStream(entry);
                Reader reader = new InputStreamReader(stream);
                CSVReader csvReader = new CSVReader(reader);
                List<String[]> csv = csvReader.readAll();
                Assertions.assertEquals(3, csv.size());

                final String[] firstRow = csv.get(0)[0].split(";");
                final String[] secondRow = csv.get(1)[0].split(";");
                final String[] thirdRow = csv.get(2)[0].split(";");

                Assertions.assertArrayEquals(new String[]{"test", "значение"}, firstRow);
                Assertions.assertArrayEquals(new String[]{"test 1", "значение 1"}, secondRow);
                Assertions.assertArrayEquals(new String[]{"test 2", "значение 2"}, thirdRow);

                return;
            }

        }
    }
}