package person.billtsui.framework.module.component;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import person.billtsui.framework.common.utils.JacksonUtil;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author billtsui
 * @date 2021/3/23
 */
@Component
public class ModuleManager {
    private Map<String, ModuleConfig> moduleConfigMap;

    @Value("classpath:module_config.json")
    private Resource moduleConfigJson;

    private ModuleManager() {
    }

    @PostConstruct
    private void initModuleConfig() throws IOException {
        String json = new String(moduleConfigJson.getInputStream().readAllBytes());
        List<ModuleConfig> moduleConfigList = JacksonUtil.fromJsonToList(json, ModuleConfig.class);

        if (CollectionUtils.isNotEmpty(moduleConfigList)) {
            moduleConfigList.forEach(moduleConfig -> {
                moduleConfigMap.put(moduleConfig.getName(), moduleConfig);
                moduleConfig.getProcessors().forEach(processorConfig -> processorConfig.setModule(moduleConfig.getName()));
            });
        }

    }

    public ModuleConfig getModule(String moduleName) {
        if (MapUtils.isNotEmpty(moduleConfigMap)) {
            return moduleConfigMap.get(moduleName);
        }

        return null;
    }
}
