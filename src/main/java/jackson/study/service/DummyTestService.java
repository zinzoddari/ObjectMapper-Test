package jackson.study.service;

import jackson.study.config.client.dto.TodoResponse;
import jackson.study.config.client.dummy.DummyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DummyTestService {

    private final DummyClient dummyClient;

    public TodoResponse getTodo(final Long id) {
        return dummyClient.get("/todos/" + id, new ParameterizedTypeReference<>() {
        });
    }
}
