package person.billtsui.framework.module.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author billtsui
 * @date 2021/3/22
 */
public class ModuleConfig {
    private String name;
    private List<ProcessorConfig> processors;
    private Map<String, ProcessorConfig> processorMap = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProcessorConfig> getProcessors() {
        return processors;
    }

    public void setProcessors(List<ProcessorConfig> processors) {
        this.processors = processors;
        processors.forEach(processorConfig -> processorMap.put(processorConfig.getModule(), processorConfig));
    }

    public Map<String, ProcessorConfig> getProcessorMap() {
        return processorMap;
    }
}
