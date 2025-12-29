package jackson.study.config.client.dummy;

import com.fasterxml.jackson.databind.ObjectMapper;
import jackson.study.config.JsonConfigHelper;

/**
 * DummyClient 전용 "스키마 변경 감지용(ObjectMapper FAIL 구성)" {@link ObjectMapper}를
 * 정적(singleton) 형태로 제공하는 지원 클래스입니다.
 *
 * <p>{@link DummyClientObjectMapperFactory}를 이용하여
 * {@code FAIL_ON_UNKNOWN_PROPERTIES = true} 옵션이 적용된 ObjectMapper를 생성하고,
 * 정적 초기화 블록에서 단 한 번만 생성하여 재사용합니다.</p>
 *
 * <p>주로 테스트 환경에서 외부 API 응답 필드 변경 여부를 감지하기 위해,
 * 역직렬화 실패를 유도하는 매퍼가 필요할 때 사용됩니다.</p>
 */
public class DummyClientFailObjectMapperSupport {

    private static final ObjectMapper objectMapper;

    static {
        JsonConfigHelper jsonConfigHelper = new JsonConfigHelper();
        DummyClientObjectMapperFactory factory =
                new DummyClientObjectMapperFactory(jsonConfigHelper.getJsonConfig());
        objectMapper = factory.createFailDummyClientObjectMapper();
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
