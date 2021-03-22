package person.billtsui.framework.module.component;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import person.billtsui.framework.module.Appender;
import person.billtsui.framework.module.Interceptor;
import person.billtsui.framework.module.Loader;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author billtsui
 * @date 2021/3/22
 */
@Component
public class ComponentManager implements ApplicationListener<ContextRefreshedEvent> {
    private volatile Map<String, Interceptor> interceptorMap = Collections.emptyMap();
    private volatile Map<String, Loader> loaderMap = Collections.emptyMap();
    private volatile Map<String, Appender> appenderMap = Collections.emptyMap();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, Interceptor> interceptorMap = this.getBeanMap(event.getApplicationContext(), Interceptor.class);
        Map<String, Loader> loaderMap = this.getBeanMap(event.getApplicationContext(), Loader.class);
        Map<String, Appender> appenderMap = this.getBeanMap(event.getApplicationContext(), Appender.class);
        this.interceptorMap = interceptorMap;
        this.loaderMap = loaderMap;
        this.appenderMap = appenderMap;
    }

    private <T> Map<String, T> getBeanMap(ApplicationContext context, Class<T> type) {
        Map<String, T> map = new HashMap<>();
        for (T bean : context.getBeansOfType(type).values()) {
            Class beanClass = bean.getClass();
            map.put(beanClass.getSimpleName(), bean);
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    public <P, R> Appender<P, R> getAppender(String name) {
        return (Appender<P, R>) this.find(name, appenderMap);
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    public <P, R> Loader<P, R> getLoader(String name) {
        return (Loader<P, R>) this.find(name, loaderMap);
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    public <P extends ParentParam, R> Interceptor<P, R> getInterceptor(String name) {
        return (Interceptor<P, R>) this.find(name, interceptorMap);
    }

    private <T> T find(String name, Map<String, T> map) {
        T component = map.get(name);
        if (null == component) {
            throw new ComponentInitException("No component with name " + name);
        }
        return component;
    }
}
