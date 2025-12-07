package jackson.study.config.client.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

@JsonTest
class PostsResponseTest {

    @Autowired
    private JacksonTester<PostsResponse> jsonTester;

    @Test
    @DisplayName("json 문자열을 객체로 성공적으로 매핑합니다.")
    void readValue() throws IOException {
        //given
        final String json = """
                {
                    "posts": [
                        {
                            "id": 1,
                            "title": "His mother had always taught him",
                            "body": "His mother had always taught him not to ever think of himself as better than others. He'd tried to live by this motto. He never looked down on those who were less fortunate or who had less money than him. But the stupidity of the group of people he was talking to made him change his mind.",
                            "tags": [
                                "history",
                                "american",
                                "crime"
                            ],
                            "reactions": {
                                "likes": 192,
                                "dislikes": 25
                            },
                            "views": 305,
                            "userId": 121
                        }
                    ],
                    "total": 251,
                    "skip": 0,
                    "limit": 30
                }
                """;

        //when
        final PostsResponse response = jsonTester.parseObject(json);

        //then
        assertSoftly(it -> {
            it.assertThat(response.getPosts().get(0).getId()).isEqualTo(1);
            it.assertThat(response.getPosts().get(0).getTitle()).isEqualTo("His mother had always taught him");
            it.assertThat(response.getPosts().get(0).getBody()).isNotNull();
        });
    }
}
