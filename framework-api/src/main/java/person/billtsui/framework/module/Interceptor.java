package person.billtsui.framework.module;

import person.billtsui.framework.module.component.ModuleParam;

/**
 * @author billtsui
 * @date 2021/3/22
 */
public interface Interceptor<P extends ModuleParam, R> {
    /**
     * 前置拦截器
     */
    void beforeProcess();

    /**
     * 后置拦截器
     */
    void afterProcess();
}
