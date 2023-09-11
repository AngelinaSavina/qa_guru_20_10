package guru.qa.hometask;

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
        try (ZipFile zipFile = new ZipFile("src/test/resources/qa_guru_20_10_HomeTask.zip")) {
            Enumeration<? extends ZipEntry> zipEntries = zipFile.entries();
            ZipEntry entry;
            while ((entry = zipEntries.nextElement()) != null) { //условие, что цикл продолжается пока следующий файл не будет ноль
                final String name = entry.getName();
                if (name.contains("sample.csv")) {
                    InputStream stream = zipFile.getInputStream(entry);
                    Reader reader = new InputStreamReader(stream);
                    CSVReader csvReader = new CSVReader(reader);
                    List<String[]> content = csvReader.readAll();

                    Assertions.assertEquals(3,content.size());
                    final String[] firstRow = content.get(0)[0].split(";");
                    final String[] secondRow = content.get(1)[0].split(";");
                    final String[] thirdRow = content.get(2)[0].split(";");

                    Assertions.assertArrayEquals(new String[] {"test", "значение"},firstRow);
                    Assertions.assertArrayEquals(new String[] {"test 1", "значение 1"},secondRow);
                    Assertions.assertArrayEquals(new String[] {"test 2", "значение 2"},thirdRow);

                    break;
                }
            }
        }
    }
}

