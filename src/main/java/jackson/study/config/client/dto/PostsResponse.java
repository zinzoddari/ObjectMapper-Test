package jackson.study.config.client.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostsResponse {

    @JsonProperty("posts")
    @JsonFormat
    private List<Post> posts;

    @JsonProperty("total")
    private Long total;

    @JsonProperty("skip")
    private Long skip;

    @JsonProperty("limit")
    private Long limit;

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Post {

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("title")
        private String title;

        @JsonProperty("body")
        private String body;

        @JsonProperty("tags")
        private List<String> tags;

        @JsonProperty("views")
        private Integer views;

        @JsonProperty("userId")
        private Integer userId;

        @JsonProperty("reactions")
        private Reactions reactions;

        @Getter
        @ToString
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Reactions {

            @JsonProperty("likes")
            private Integer likes;

            @JsonProperty("dislikes")
            private Integer dislikes;
        }
    }
}
