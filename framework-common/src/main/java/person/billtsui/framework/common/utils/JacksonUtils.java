package person.billtsui.framework.common.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author billtsui
 * @date 2021/3/17
 */
@Slf4j
public class JacksonUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            //忽略未知的字段
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private JacksonUtils() {
    }

    public static <T> T fromJson(String json, Class<T> type) {
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            log.error("JacksonUtils fromJson error", e);
            return null;
        }
    }

    public static <T> List<T> fromJsonToList(String json, Class<T> type) {
        TypeFactory typeFactory = OBJECT_MAPPER.getTypeFactory();
        try {
            return OBJECT_MAPPER.readValue(json, typeFactory.constructCollectionType(List.class, type));
        } catch (Exception e) {
            log.error("JacksonUtils fromJsonToList error", e);
            return null;
        }
    }

    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            log.error("JacksonUtils toJson error", e);
            return null;
        }
    }

    public static JsonNode readTree(String json) {
        try {
            return OBJECT_MAPPER.readTree(json);
        } catch (Exception e) {
            log.error("JacksonUtils readTree error", e);
            return null;
        }
    }

    public static JsonNode readTreeNullable(String json) {
        JsonNode jsonNode = readTree(json);
        if (jsonNode == null || jsonNode.isNull() || jsonNode.isMissingNode()) {
            return null;
        }
        return jsonNode;
    }

    public static ArrayNode readTreeAsArray(String json) {
        JsonNode node = readTreeNullable(json);
        if (null == node) {
            return null;
        }

        if (node.isArray()) {
            return (ArrayNode) node;
        }

        log.error(String.format("JacksonUtils readTreeAsArray error.json:%s", json));
        return null;
    }

    public static JsonNode valueToTree(Object object) {
        return OBJECT_MAPPER.valueToTree(object);
    }

    public static boolean hasFieldNotBlank(JsonNode node, String fieldName) {
        return node.hasNonNull(fieldName) && StringUtils.isNotBlank(node.get(fieldName).asText());
    }

    public static ObjectNode objectNode() {
        return OBJECT_MAPPER.createObjectNode();
    }

    public static ArrayNode arrayNode() {
        return OBJECT_MAPPER.createArrayNode();
    }
}
