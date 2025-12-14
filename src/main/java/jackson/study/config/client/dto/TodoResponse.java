package jackson.study.config.client.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoResponse {

    private Long userId;

    private Long id;

    private String title;

    private boolean completed;
}
