package person.billtsui.framework.module.component;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

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
        ModuleConfig moduleConfig = this.getModuleConfig(param.getModule());
        return null;
    }

    ModuleConfig getModuleConfig(String moduleName) {
        if (null == moduleManager) {
            return null;
        }
        ModuleConfig module = moduleManager.getModule(moduleName);

        return Optional.ofNullable(module).orElse(null);
    }
}
