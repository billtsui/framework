package person.billtsui.framework.module;

import person.billtsui.framework.module.component.ParentParam;

/**
 * @author billtsui
 * @date 2021/3/22
 */
public interface Interceptor<P extends ParentParam, R> {
    /**
     * 前置拦截器
     * 在执行execute前执行
     */
    void beforeExecute();

    /**
     * 后置拦截器
     * 在执行execute后执行
     */
    void afterExecute();
}
