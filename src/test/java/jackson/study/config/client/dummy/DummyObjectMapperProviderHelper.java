package jackson.study.config.client.dummy;

import com.fasterxml.jackson.databind.ObjectMapper;
import jackson.study.config.JsonConfigHelper;

public class DummyObjectMapperProviderHelper {

    private final DummyObjectMapperProvider objectMapperProvider;

    public DummyObjectMapperProviderHelper() {
        JsonConfigHelper jsonConfigHelper = new JsonConfigHelper();
        this.objectMapperProvider = new DummyObjectMapperProvider(jsonConfigHelper.getTestObjectMapper());
    }

    public ObjectMapper getDummyObjetMapper() {
        return objectMapperProvider.getDummyObjectMapper();
    }
}