package person.billtsui.framework.module.component;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import person.billtsui.framework.module.Appender;
import person.billtsui.framework.module.Interceptor;
import person.billtsui.framework.module.Loader;
import person.billtsui.framework.module.component.model.AppenderConfig;
import person.billtsui.framework.module.component.model.LoaderConfig;

import javax.annotation.Nonnull;
import java.util.*;

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

    public <P, R> List<Appender<P, R>> getAppenders(List<String> names) {
        if (CollectionUtils.isEmpty(names)) {
            return Collections.emptyList();
        }
        List<Appender<P, R>> result = new ArrayList<>();
        for (String appenderName : names) {
            result.add(getAppender(appenderName));
        }

        return result;
    }

    public <P, R> List<ConfiguredAppender<P, R>> getAppenderList(List<AppenderConfig> configs) {
        if (CollectionUtils.isEmpty(configs)) {
            return Collections.emptyList();
        }
        List<ConfiguredAppender<P, R>> appenderList = new ArrayList<>();
        for (AppenderConfig config : configs) {
            appenderList.add(new ConfiguredAppender<>(this.getAppender(config.getName()), config));
        }
        return appenderList;
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    public <P, R> Loader<P, R> getLoader(String name) {
        return (Loader<P, R>) this.find(name, loaderMap);
    }

    public <P, R> List<Loader<P, R>> getLoaders(List<String> names) {
        if (CollectionUtils.isEmpty(names)) {
            return Collections.emptyList();
        }

        List<Loader<P, R>> result = new ArrayList<>();
        for (String loaderName : names) {
            result.add(getLoader(loaderName));
        }
        return result;
    }

    public <P, R> List<ConfiguredLoader<P, R>> getLoaderList(List<LoaderConfig> configs) {
        if (CollectionUtils.isEmpty(configs)) {
            return Collections.emptyList();
        }
        List<ConfiguredLoader<P, R>> loaderList = new ArrayList<>();
        for (LoaderConfig config : configs) {
            loaderList.add(new ConfiguredLoader<>(this.getLoader(config.getName()), config));
        }
        return loaderList;
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    public <P extends ModuleParam, R> Interceptor<P, R> getInterceptor(String name) {
        return (Interceptor<P, R>) this.find(name, interceptorMap);
    }

    public <P extends ModuleParam, R> List<Interceptor<P, R>> getInterceptors(List<String> names) {
        if (CollectionUtils.isEmpty(names)) {
            return Collections.emptyList();
        }

        List<Interceptor<P, R>> result = new ArrayList<>();
        names.forEach(name -> {
            result.add(this.getInterceptor(name));
        });

        return result;
    }

    private <T> T find(String name, Map<String, T> map) {
        T component = map.get(name);
        if (null == component) {
            throw new ComponentInitException("No component with name " + name);
        }
        return component;
    }
}
