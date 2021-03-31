package person.billtsui.framework.module.component.model;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author billtsui
 * @date 2021/3/22
 */
@Data
public class ModuleConfig {
    private String name;
    private List<ProcessorConfig> processors;

    public ProcessorConfig getConfig(String name) {
        if (CollectionUtils.isEmpty(processors)) {
            return null;
        }

        Optional<ProcessorConfig> first = processors.stream().filter(processorConfig -> processorConfig.getName().equals(name)).findFirst();
        if (first.isPresent()) {
            return first.get();
        } else {
            return null;
        }
    }
}
