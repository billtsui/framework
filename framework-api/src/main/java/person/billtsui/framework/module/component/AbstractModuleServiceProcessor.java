package person.billtsui.framework.module.component;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import person.billtsui.framework.module.Interceptor;
import person.billtsui.framework.module.Loader;
import person.billtsui.framework.module.component.model.LoaderConfig;
import person.billtsui.framework.module.component.model.ModuleConfig;
import person.billtsui.framework.module.component.model.ProcessorConfig;

import java.util.Collections;
import java.util.List;


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
            responseEntity = this.excute(loader, param);
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

    private R excute(ConfiguredLoader<P, R> loader, P param) {
        LoaderConfig loaderConfig = loader.getLoaderConfig();
        R result = this.executeLoader(loader, param);

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
}
