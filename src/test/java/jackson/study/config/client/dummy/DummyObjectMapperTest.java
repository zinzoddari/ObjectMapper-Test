package jackson.study.config.client.dummy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * DummyClient에서 사용 될 ObjectMapper의 옵션 등을 확인합니다.
 */
class DummyObjectMapperTest {

    private final ObjectMapper objectMapper = DummyClientObjectMapperSupport.getObjectMapper();

    @Test
    @DisplayName("DTO에 존재하지 않는 값이 존재하더라도, 역직렬화에 성공합니다.")
    void readValue() {
        //given
        final String json = """
                {
                  "hello": "world"
                }
                """;

        // when & then
        assertDoesNotThrow(() -> objectMapper.readValue(json, TodoResponse.class));
    }
}
