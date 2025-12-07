package jackson.study.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConfigHelper {

    private JsonConfig jsonConfig = new JsonConfig();

    public ObjectMapper getTestObjectMapper() {
        return jsonConfig.objectMapperConfig();
    }
}