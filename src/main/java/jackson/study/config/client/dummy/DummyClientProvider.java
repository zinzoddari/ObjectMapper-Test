package jackson.study.config.client.dummy;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * DummyClient 인스턴스를 생성하는 구성 클래스입니다.
 *
 * <p>DummyClient가 사용할 {@link RestTemplate}을 정의하며,
 * 전역 ObjectMapper와 분리된 DummyClient 전용 {@link ObjectMapper}를 기반으로
 * JSON 응답을 역직렬화할 수 있도록 {@link MappingJackson2HttpMessageConverter}를 추가 구성합니다.</p>
 *
 * <p>생성된 RestTemplate은 외부 Dummy API(Base URI는 설정 값 사용) 호출에 사용됩니다.</p>
 */
@Configuration
@RequiredArgsConstructor
class DummyClientProvider {

    private final DummyClientObjectMapperFactory objectMapperFactory;

    @Bean
    public DummyClient dummyClient(@Value("${api.dummy.base-uri}") final String baseUri, final RestTemplateBuilder builder) {
       final ObjectMapper dummyObjectMapper = objectMapperFactory.createDummyClientObjectMapper();

       // 1. ObjectMapper를 사용하는 JSON <-> DTO 변환기 생성
       final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(dummyObjectMapper);

       // 2. 해당 ObjectMapper를 응답 매핑에 사용하는 RestTemplate 생성
       final RestTemplate restTemplate = builder.rootUri(baseUri)
                .additionalMessageConverters(converter)
                .build();

        return new DummyClient(restTemplate);
    }
}
