package jackson.study.config.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jackson.study.config.client.dto.PostsResponse;
import jackson.study.config.client.dummy.DummyObjectMapperProviderHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class DummyObjectMapperTest {

    private final ObjectMapper objectMapper;

    public DummyObjectMapperTest() {
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
    @DisplayName("json 문자열을 객체로 성공적으로 매핑합니다.")
    void readValue() throws JsonProcessingException {
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
        final PostsResponse response = objectMapper.readValue(json, PostsResponse.class);

        //then
        assertSoftly(it -> {
            it.assertThat(response.getPosts().get(0).getId()).isEqualTo(1);
            it.assertThat(response.getPosts().get(0).getTitle()).isEqualTo("His mother had always taught him");
            it.assertThat(response.getPosts().get(0).getBody()).isNotNull();
        });
    }
}