package jackson.study.config.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
class RestTemplateConfig {

    @Bean
    public RestTemplateBuilder restTemplateBuilderConfig() {
        RestTemplateBuilder builder = new RestTemplateBuilder();

        return builder.readTimeout(Duration.ofSeconds(30))
                .connectTimeout(Duration.ofSeconds(30));
    }
}
