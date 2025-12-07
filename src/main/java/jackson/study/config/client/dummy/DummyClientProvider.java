package jackson.study.config.client.dummy;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
class DummyClientProvider {

    private final DummyObjectMapperProvider objectMapperProvider;

    @Bean
    public DummyClient dummyClient(@Value("${api.dummy.base-uri}") final String baseUri, final RestTemplateBuilder builder) {
        final ObjectMapper dummyObjectMapper = objectMapperProvider.getDummyObjectMapper();

       final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(dummyObjectMapper);

        final RestTemplate restTemplate = builder.rootUri(baseUri)
                .additionalMessageConverters(converter)
                .build();

        return new DummyClient(restTemplate);
    }
}
