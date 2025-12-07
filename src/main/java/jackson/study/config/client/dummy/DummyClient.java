package jackson.study.config.client.dummy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
public class DummyClient {

    private final RestTemplate restTemplate;

    public <R> R get(final String uri, final ParameterizedTypeReference<R> reference) {
        log.debug("request: {}, {}", uri);

        RequestEntity<Void> request = RequestEntity
                .get(uri)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        ResponseEntity<R> response = restTemplate.exchange(request, reference);

        log.debug("response: {}, {}", uri, response);

        if (response.getStatusCode().isError()) {
            throw new RuntimeException();
        }

        if (ObjectUtils.isEmpty(response.getBody())) {
            return null;
        }

        return response.getBody();
    }
}
