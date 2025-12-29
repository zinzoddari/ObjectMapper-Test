package jackson.study.config.client.dummy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

@SpringBootTest
class DummyClientSpringBootTest {

    @Autowired
    private DummyClient dummyClient;

    @Test
    @DisplayName("오늘의 할 일을 조회하여 TodoResponse에 매핑이 잘 되는 것을 확인합니다.")
    void getTodo() {
        // given
        final String uri = "/todos/1";

        final Long userId = 1L;
        final Long id = 1L;
        final String title = "delectus aut autem";
        final Boolean completed = false;

        // when
        final TodoResponse response = dummyClient.get(uri, new ParameterizedTypeReference<>() {
        });

        // then
        assertSoftly(it -> {
            it.assertThat(response.getUserId()).isEqualTo(userId);
            it.assertThat(response.getId()).isEqualTo(id);
            it.assertThat(response.getTitle()).isEqualTo(title);
            it.assertThat(response.isCompleted()).isEqualTo(completed);
        });
    }
}
