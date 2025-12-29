package jackson.study.config.client.dummy;

import com.fasterxml.jackson.databind.ObjectMapper;
import jackson.study.config.JsonConfigHelper;

/**
 * DummyClient 전용 기본 {@link ObjectMapper}를 싱글톤 형태로 제공하는 지원 클래스입니다.
 *
 * <p>{@link DummyClientObjectMapperFactory}를 통해 생성된 ObjectMapper를
 * 정적 초기화 블럭에서 단 한 번 생성하여 재사용하며,
 * DI(Spring) 환경 없이도 DummyClient용 매퍼에 접근할 수 있도록 합니다.</p>
 *
 * <p>DummyClient가 사용하는 JSON 역직렬화 정책을 테스트 또는 별도 환경에서
 * 간단히 가져다 사용할 수 있도록 하기 위한 유틸리티 성격의 클래스입니다.</p>
 */
public class DummyClientObjectMapperSupport {

    private static final ObjectMapper objectMapper;

    static {
        JsonConfigHelper jsonConfigHelper = new JsonConfigHelper();
        DummyClientObjectMapperFactory factory =
                new DummyClientObjectMapperFactory(jsonConfigHelper.getJsonConfig());
        objectMapper = factory.createDummyClientObjectMapper();
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
