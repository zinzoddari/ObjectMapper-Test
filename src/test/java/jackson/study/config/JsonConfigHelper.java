package jackson.study.config;

/**
 * {@link JsonConfig} 인스턴스를 단순히 보유하고 제공하는 헬퍼 클래스입니다.
 *
 * <p>DI 환경 없이도 {@link JsonConfig}를 직접 생성·참조할 수 있도록 하는 용도로 사용되며,
 * 테스트 또는 ObjectMapper 구성 시 JsonConfig 설정 객체가 필요한 경우
 * 간단히 접근할 수 있는 전달자 역할을 수행합니다.</p>
 */
public class JsonConfigHelper {

    private final JsonConfig jsonConfig = new JsonConfig();

    public JsonConfig getJsonConfig() {
        return jsonConfig;
    }
}
