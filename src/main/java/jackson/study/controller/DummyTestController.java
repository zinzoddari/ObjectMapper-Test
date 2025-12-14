package jackson.study.controller;

import jackson.study.config.client.dto.TodoResponse;
import jackson.study.service.DummyTestService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class DummyTestController {

    private final DummyTestService dummyTestService;

    @GetMapping("/test/dummy/{id}")
    public TodoResponse getDummyTodo(@PathVariable final Long id) {
        return dummyTestService.getTodo(id);
    }
}
