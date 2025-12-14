package jackson.study.config.client.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jackson.study.config.client.dummy.DummyObjectMapperProviderHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class TodoResponseTest {

    private final ObjectMapper objectMapper;

    private TodoResponseTest() {
        final DummyObjectMapperProviderHelper helper = new DummyObjectMapperProviderHelper();

        this.objectMapper = helper.getDummyObjetMapper();
    }

    @Test
    @DisplayName("존재하지 않는 key의 경우, 예외가 발생합니다.")
    void ignoreKey() {
        //given
        final String json = """
                {
                    "hello": "hi"
                }
                """;

        //when & then
        assertThatThrownBy(() -> objectMapper.readValue(json, PostsResponse.class))
                .isInstanceOf(UnrecognizedPropertyException.class);
    }

    @Test
    @DisplayName("json 문자열을 TodoResponse 객체로 성공적으로 매핑합니다.")
    void readValue() throws JsonProcessingException {
        //given
        final String json = """
                {
                  "userId": 1,
                  "id": 1,
                  "title": "delectus aut autem",
                  "completed": false
                }
                """;

        //when
        final TodoResponse response = objectMapper.readValue(json, TodoResponse.class);

        //then
        assertSoftly(it -> {
            it.assertThat(response.getUserId()).isEqualTo(1L);
            it.assertThat(response.getId()).isEqualTo(1L);
            it.assertThat(response.getTitle()).isEqualTo("delectus aut autem");
            it.assertThat(response.isCompleted()).isEqualTo(false);
        });
    }
}
