package jackson.study.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * JSON 직렬화·역직렬화에 사용되는 Jackson 공통 설정을 제공하는 구성 클래스입니다.
 *
 * <p>ObjectMapper 인스턴스 생성 시 재사용할 수 있는 기본 {@link JsonMapper.Builder}를 반환하며,
 * 날짜·시간 처리 및 알 수 없는 필드 허용 여부 등 공통 정책을 통합적으로 관리합니다.
 * 이 설정은 전역 ObjectMapper가 아닌, 개별 API 클라이언트 전용 매퍼 생성 시 재사용하기 위한 목적을 가집니다.</p>
 */
@Configuration
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JsonConfig {

    /**
     * 기본 JSON 매퍼 설정이 적용된 {@link JsonMapper.Builder}를 생성합니다.
     *
     * <p>아래 옵션을 포함한 공통 기본값을 구성합니다:
     * <ul>
     *     <li>{@link SerializationFeature#WRITE_DATES_AS_TIMESTAMPS} 비활성화
     *         → Java 날짜/시간 타입을 숫자 타임스탬프가 아니라 ISO-8601 문자열 형태로 직렬화</li>
     *     <li>{@link DeserializationFeature#FAIL_ON_UNKNOWN_PROPERTIES} 비활성화
     *         → JSON에 정의되지 않은 필드가 존재해도 역직렬화 오류를 발생시키지 않음</li>
     *     <li>{@link JavaTimeModule} 추가
     *         → {@code LocalDate}, {@code LocalDateTime} 등 Java Time API 타입의 직렬화/역직렬화 지원</li>
     * </ul>
     * </p>
     *
     * @return 공통 Jackson 설정이 적용된 {@link JsonMapper.Builder} 인스턴스
     */
    public JsonMapper.Builder createDefaultJsonMapperBuilder() {
        return JsonMapper.builder()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .addModules(new JavaTimeModule());
    }
}
