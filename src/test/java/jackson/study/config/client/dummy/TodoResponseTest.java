package jackson.study.config.client.dummy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class TodoResponseTest {

    private final ObjectMapper objectMapper = DummyClientObjectMapperSupport.getObjectMapper();

    @Test
    @DisplayName("성공적으로 JSON 값을 TodoResponse로 역직렬화 합니다.")
    void readValue() throws JsonProcessingException {
        // given
        final Long userId = 1L;
        final Long id = 1L;
        final String title = "delectus aut autem";
        final Boolean completed = false;

        final String json = """
                    {
                      "userId": ${userId},
                      "id": ${id},
                      "title": "${title}",
                      "completed": ${completed}
                    }
                """.replace("${userId}", userId.toString())
                .replace("${id}", id.toString())
                .replace("${title}", title)
                .replace("${completed}", completed.toString());

        // when
        final TodoResponse response = objectMapper.readValue(json, TodoResponse.class);

        // then
        assertSoftly(it -> {
            it.assertThat(response.getUserId()).isEqualTo(userId);
            it.assertThat(response.getId()).isEqualTo(id);
            it.assertThat(response.getTitle()).isEqualTo(title);
            it.assertThat(response.isCompleted()).isEqualTo(completed);
        });
    }
}
