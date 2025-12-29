package jackson.study.config.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * RestTemplate 생성에 사용될 공통 {@link RestTemplateBuilder} 설정을 제공하는 구성 클래스입니다.
 *
 * <p>네트워크 호출 시 적용할 기본 타임아웃 정책(Read/Connect 30초)을 중앙화하여 관리하며,
 * 각 서비스별 클라이언트가 RestTemplate을 생성할 때 동일한 네트워크 설정을 사용할 수 있도록 합니다.
 * 이 클래스는 RestTemplate 자체를 Bean으로 등록하지 않고, Builder만 제공한다는 점에서
 * 개별 클라이언트가 독립적으로 RestTemplate을 생성하도록 하는 구조적 의도를 가집니다.</p>
 */
@Configuration
class RestTemplateConfig {

    /**
     * RestTemplate 인스턴스 생성을 위한 기본 {@link RestTemplateBuilder}를 구성합니다.
     *
     * <p>생성된 Builder에는 다음 기본 타임아웃 설정이 적용됩니다:
     * <ul>
     *     <li>읽기(Read) 타임아웃: 30초</li>
     *     <li>연결(Connection) 타임아웃: 30초</li>
     * </ul>
     * 이를 기반으로 다른 구성 클래스에서 RestTemplate을 생성할 때 공통 네트워크 정책을 일관되게 적용할 수 있습니다.</p>
     *
     * @return 공통 타임아웃이 설정된 {@link RestTemplateBuilder} 빈
     */
    @Bean
    public RestTemplateBuilder restTemplateBuilderConfig() {
        final RestTemplateBuilder builder = new RestTemplateBuilder();

        return builder.readTimeout(Duration.ofSeconds(30))
                .connectTimeout(Duration.ofSeconds(30));
    }
}
