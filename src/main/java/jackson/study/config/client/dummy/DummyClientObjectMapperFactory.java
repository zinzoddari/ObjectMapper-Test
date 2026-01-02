package jackson.study.config.client.dummy;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jackson.study.config.JsonConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * DummyClient 전용 {@link ObjectMapper} 인스턴스를 생성하는 팩토리 클래스입니다.
 *
 * <p>전역 ObjectMapper 설정과 분리된 별도 JSON 매퍼 구성을 제공하기 위해 사용됩니다.
 * {@link JsonConfig}에서 정의된 공통 빌더 설정을 기반으로 객체를 생성하며,
 * 기본 매퍼와 스키마 변경 감지(FAIL_ON_UNKNOWN_PROPERTIES) 테스트용 매퍼를 각각 제공합니다.</p>
 */
@Configuration
@RequiredArgsConstructor
class DummyClientObjectMapperFactory {

    private final JsonConfig jsonConfig;

    /**
     * DummyClient 전용 기본 {@link ObjectMapper}를 생성합니다.
     *
     * <p>{@link JsonConfig}에서 정의한 기본 JSON 매퍼 설정을 그대로 사용하여 인스턴스를 생성하며,
     * 서비스 전역 ObjectMapper와 격리된 별도의 매퍼가 필요할 때 활용할 수 있습니다.
     * 주로 {@link DummyClient}의 외부 API 응답 역직렬화 또는 해당 전용 테스트 환경을 구성할 때 사용됩니다.</p>
     *
     * @return DummyClient용 기본 ObjectMapper 인스턴스
     */
    public ObjectMapper createDummyClientObjectMapper() {
        return jsonConfig.createDefaultJsonMapperBuilder()
                .build();
    }

    /**
     * 테스트용 ObjectMapper를 생성합니다.
     *
     * <p>외부 API 응답 필드가 변경(추가/삭제)되었을 때 역직렬화 오류를 강제로 발생시키기 위한
     * 전용 ObjectMapper입니다. 기본 매퍼 설정을 복사한 후
     * {@link DeserializationFeature#FAIL_ON_UNKNOWN_PROPERTIES} 옵션을 활성화하여,
     * JSON에 정의되지 않은 필드가 포함되면 예외가 발생하도록 구성합니다.</p>
     *
     * @return 실패(깨지는) 테스트 수행을 위한 전용 ObjectMapper 인스턴스
     */
    public ObjectMapper createFailDummyClientObjectMapper() {
        return jsonConfig.createDefaultJsonMapperBuilder()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
            .build();
    }
}
