package jackson.study.config.client;

import jackson.study.config.client.dto.PostsResponse;
import jackson.study.config.client.dummy.DummyClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

@SpringBootTest
// @Import(DummyClientProvider.class)
class DummyClientTest {

    @Autowired
    private DummyClient dummyClient;

    @Test
    @DisplayName("Dummy API에 직접 호출하여 게시물을 조회합니다.")
    void getPosts() {
        //when
        final PostsResponse response = dummyClient.get("/posts", new ParameterizedTypeReference<>() {
        });

        //then
        assertSoftly(it -> {
            it.assertThat(response.getPosts().get(0).getId()).isEqualTo(1);
            it.assertThat(response.getPosts().get(0).getTitle()).isEqualTo("His mother had always taught him");
            it.assertThat(response.getPosts().get(0).getBody()).isNotNull();
        });
    }
}