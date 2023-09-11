package guru.qa.hometask;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonReaderTest {
    ClassLoader classLoader = JsonReaderTest.class.getClassLoader();

    @Test
    @DisplayName("Jackson parse json")
    void jacksonReaderJson() throws Exception {
        InputStream inputStream = classLoader.getResourceAsStream("json_example.json");
        ObjectMapper objectMapper = new ObjectMapper();
        assert inputStream != null;
        JsonNode jsonNode = objectMapper.readTree(new InputStreamReader(inputStream));

        assertThat(jsonNode.get("boolean").asBoolean()).isEqualTo(true);
        assertThat(jsonNode.get("number").asInt()).isEqualTo(123);
        assertThat(jsonNode.get("string").asText()).isEqualTo("Hello World");


    }
}
