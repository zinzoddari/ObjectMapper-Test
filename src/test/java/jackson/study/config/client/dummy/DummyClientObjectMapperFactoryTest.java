package jackson.study.config.client.dummy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DummyClientObjectMapperFactoryTest {

    private static ObjectMapper objectMapper = DummyClientFailObjectMapperSupport.getObjectMapper();

    @Test
    @DisplayName("DTO에 없는 필드가 들어오면 역직렬화에 실패합니다.")
    void fail() {
        //given
        final String json = """
                {
                  "hello": "world"
                }
                """;

        //when & then
        assertThrows(UnrecognizedPropertyException.class,
                () -> objectMapper.readValue(json, TodoResponse.class));
    }
}
