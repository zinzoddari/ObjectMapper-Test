package jackson.study.config.client.dummy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

/**
 * 외부 Dummy API 호출을 수행하는 간단한 HTTP 클라이언트입니다.
 *
 * <p>{@link RestTemplate} 을 사용하여 외부 요청을 처리하며,
 * JSON 응답은 구성된 Jackson 메시지 컨버터를 통해 역직렬화됩니다.
 * 이 클라이언트는 Dummy 기관에 대한 전용 요청 수행을 목적으로 합니다.</p>
 *
 * <p>생성 시 주입되는 {@link RestTemplate}에 따라,
 * 해당 클라이언트가 사용하는 JSON 매핑 정책(ObjectMapper 구성) 역시 함께 결정됩니다.</p>
 */
@Slf4j
@RequiredArgsConstructor
public class DummyClient {

    private final RestTemplate restTemplate;

    /**
     * 지정된 URI로 HTTP GET 요청을 수행하고 JSON 본문을 역직렬화하여 반환합니다.
     *
     * <p>{@link ParameterizedTypeReference} 를 전달받아 제네릭 타입까지 보존된 형태로 응답 본문을 역직렬화합니다.
     * 요청 헤더에는 {@code Accept: application/json} 이 포함되며,
     * 응답 상태코드가 4xx 또는 5xx인 경우 예외를 발생시킵니다.</p>
     *
     * <p>응답 본문이 비어 있는 경우 {@code null} 을 반환합니다.</p>
     *
     * @param uri        요청 대상 URI
     * @param reference  응답 타입 정보 (제네릭 포함)
     * @param <R>        응답 매핑에 사용될 결과 타입
     * @return           역직렬화된 응답 객체 또는 본문이 없는 경우 {@code null}
     * @throws RuntimeException 응답 상태가 오류(4xx/5xx)인 경우
     */
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
