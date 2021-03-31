package person.billtsui.framework.module.component;

import org.springframework.beans.factory.annotation.Autowired;
import person.billtsui.framework.module.component.model.ModuleConfig;
import person.billtsui.framework.module.component.model.ProcessorConfig;


/**
 * @author billtsui
 * @date 2021/3/23
 */
public abstract class AbstractModuleServiceProcessor<P extends ModuleParam, R> implements ModuleServiceProcessor<P, R> {

    @Autowired
    private ComponentManager componentManager;

    @Autowired
    private ModuleManager moduleManager;


    @Override
    public R handle(P param) {
        R responseEntity = null;

        ProcessorConfig processorConfig = this.getConfig(param.getModule());

        return responseEntity;
    }

    private ProcessorConfig getConfig(String module) {
        ModuleConfig moduleConfig = moduleManager.getModule(module);
        if (null != moduleConfig) {
            return moduleConfig.getConfig(this.processorName());
        }

        return null;
    }

    private String processorName() {
        return this.getClass().getSimpleName();
    }
}
