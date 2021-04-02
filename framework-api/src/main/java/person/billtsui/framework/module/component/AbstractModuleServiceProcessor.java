package person.billtsui.framework.module.component;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import person.billtsui.framework.module.Appender;
import person.billtsui.framework.module.Interceptor;
import person.billtsui.framework.module.Loader;
import person.billtsui.framework.module.component.model.AppenderConfig;
import person.billtsui.framework.module.component.model.LoaderConfig;
import person.billtsui.framework.module.component.model.ModuleConfig;
import person.billtsui.framework.module.component.model.ProcessorConfig;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * @author billtsui
 * @date 2021/3/23
 */
public abstract class AbstractModuleServiceProcessor<P extends ModuleParam, R> implements ModuleServiceProcessor<P, R> {

    @Autowired
    private ComponentManager componentManager;

    @Autowired
    private ModuleManager moduleManager;

    protected abstract Loader<P, R> getDefaultLoader();


    @Override
    public R handle(P param) {
        R responseEntity = null;

        ProcessorConfig processorConfig = this.getConfig(param.getModule());

        //获取interceptors
        List<Interceptor<P, R>> interceptors =
                processorConfig != null
                        ? this.getInterceptors(processorConfig.getInterceptors())
                        : Collections.emptyList();

        //获取loaders
        List<ConfiguredLoader<P, R>> loaders = this.getLoaders(param.getModule(), processorConfig);

        //执行前置拦截器
        for (Interceptor<P, R> interceptor : interceptors) {
            interceptor.beforeProcess(param);
        }

        for (ConfiguredLoader<P, R> loader : loaders) {
            responseEntity = this.execute(loader, param);
        }


        //执行后置拦截器
        for (Interceptor<P, R> interceptor : interceptors) {
            interceptor.afterProcess(param, responseEntity);
        }

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

    private List<Interceptor<P, R>> getInterceptors(List<String> interceptors) {
        if (CollectionUtils.isEmpty(interceptors)) {
            return Collections.emptyList();
        }

        return componentManager.getInterceptors(interceptors);
    }

    private List<ConfiguredLoader<P, R>> getLoaders(String module, ProcessorConfig processorConfig) {
        if (processorConfig != null && CollectionUtils.isNotEmpty(processorConfig.getLoaders())) {
            List<ConfiguredLoader<P, R>> loaderList = componentManager.getLoaderList(processorConfig.getLoaders());
            if (CollectionUtils.isNotEmpty(loaderList)) {
                return loaderList;
            }
        }

        Loader<P, R> defaultLoader = this.getDefaultLoader();
        if (defaultLoader != null) {
            return Collections.singletonList(new ConfiguredLoader<>(module, defaultLoader));
        }

        throw new ComponentInitException("Fail to find loaders. module=" + module);
    }

    private R execute(ConfiguredLoader<P, R> loader, P param) {
        LoaderConfig loaderConfig = loader.getLoaderConfig();
        R result = this.executeLoader(loader, param);
        if (null != result) {
            this.executeSyncAppender(loaderConfig.getSyncAppenders(), param, result);
            this.executeAsyncAppender(loaderConfig.getAsyncAppenders(), loaderConfig.getTimeout(), param, result);
        }
        return result;
    }

    private R executeLoader(ConfiguredLoader<P, R> loader, P param) {
        List<Interceptor<P, R>> interceptors = this.getInterceptors(loader.getLoaderConfig().getInterceptors());
        if (CollectionUtils.isNotEmpty(interceptors)) {
            interceptors.forEach(i -> i.beforeProcess(param));
            R result = loader.load(param);
            interceptors.forEach(i -> i.afterProcess(param, result));
            return result;
        }

        return loader.load(param);
    }

    private void executeSyncAppender(List<AppenderConfig> configs, P param, R result) {
        this.getSyncAppenderList(param.getModule(), configs)
                .forEach(a -> this.executeAppender(a, param, result));
    }

    private void executeAsyncAppender(List<AppenderConfig> configs, Long timeout, P param, R responseEntity) {
        List<ConfiguredAppender<P, R>> asyncAppenderList = this
                .getAsyncAppenderList(param.getModule(), configs);
//        if (CollectionUtils.isNotEmpty(asyncAppenderList)) {
//            Supervisor supervisor = SuperviseBuilder.createSupervisor();
//            asyncAppenderList.forEach(a ->
//                    supervisor.submit(
//                            SuperviseBuilder.build(p -> {
//                                this.executeAppender(a, p, responseEntity);
//                            }, param)
//                    ));
//
//            timeout = ResultGuardUtils.resultOrDefault(timeout, DEFAULT_ASYNC_TIMEOUT);
//            try {
//                supervisor.supervise(timeout, TimeUnit.MILLISECONDS);
//            } catch (InterruptedException e) {
//                supervisor.cancelNotRunning();
//                log.error("Supervise timeout. service provider={}, timeout={} ms.", this.getClass().getSimpleName(),
//                        timeout);
//            }
//        }
    }

    @SuppressWarnings("unchecked")
    private void executeAppender(ConfiguredAppender<P, R> appender, P param, R result) {
        List<Interceptor<P, R>> appenderInterceptors = this
                .getInterceptors(appender.getAppenderConfig().getInterceptors());
        if (CollectionUtils.isNotEmpty(appenderInterceptors)) {
            appenderInterceptors.forEach(i -> i.beforeProcess(param));
            appender.append(param, result);
            appenderInterceptors.forEach(i -> i.afterProcess(param, result));
        } else {
            appender.append(param, result);
        }
    }

    private List<ConfiguredAppender<P, R>> getSyncAppenderList(String module, List<AppenderConfig> configs) {
        return this.getAppenderList(module, configs, this::getDefaultSyncAppenderList);
    }

    private List<ConfiguredAppender<P, R>> getAsyncAppenderList(String module,List<AppenderConfig> configs) {
        return this.getAppenderList(module, configs, this::getDefaultSyncAppenderList);
    }

    protected List<Appender<P, R>> getDefaultSyncAppenderList() {
        return Collections.emptyList();
    }

    private List<ConfiguredAppender<P, R>> getAppenderList(String module, List<AppenderConfig> configs, Supplier<List<Appender<P, R>>> defaultSupplier) {
        if (CollectionUtils.isNotEmpty(configs)) {
            return componentManager.getAppenderList(configs);
        } else {
            return defaultSupplier.get().stream().map(appender -> new ConfiguredAppender<>(module, appender)).collect(Collectors.toList());
        }
    }
}
