package person.billtsui.framework.module.component;

import org.springframework.beans.factory.annotation.Autowired;


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


        return responseEntity;
    }
}
