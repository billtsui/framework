package person.billtsui.framework.module.component;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import person.billtsui.framework.module.Interceptor;
import person.billtsui.framework.module.Loader;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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


    @Override
    public R handle(P param) {
        R responseEntity = null;

        ModuleConfig moduleConfig = this.getModuleConfig(param.getModule());

        if (null == moduleConfig) {
            return null;
        }

        List<ProcessorConfig> processors = moduleConfig.getProcessors();
        if (CollectionUtils.isNotEmpty(processors)) {
            ProcessorConfig processorConfig = getProcessorConfig(moduleConfig);

            List<Interceptor<P, R>> interceptors = getInterceptors(processorConfig.getInterceptors());
            List<Loader<P, R>> loaders = getLoaders(processorConfig);

            for (Interceptor<P, R> interceptor : interceptors) {
                interceptor.beforeProcess(param);
            }

            for (Loader<P, R> loader : loaders) {
                responseEntity = this.excute(loader, param);

            }

            for (Interceptor<P, R> interceptor : interceptors) {
                interceptor.afterProcess(param, responseEntity);
            }
        }

        return responseEntity;
    }

    ModuleConfig getModuleConfig(String moduleName) {
        if (null == moduleManager) {
            return null;
        }
        ModuleConfig module = moduleManager.getModule(moduleName);

        return Optional.ofNullable(module).orElse(null);
    }

    ProcessorConfig getProcessorConfig(ModuleConfig moduleConfig) {
        Optional<ProcessorConfig> first = moduleConfig.getProcessors().stream().filter(module -> module.getName().equals(getProcessorName())).findFirst();
        return first.orElse(null);
    }

    String getProcessorName() {
        return this.getClass().getSimpleName();
    }

    List<Interceptor<P, R>> getInterceptors(List<String> names) {
        if (CollectionUtils.isNotEmpty(names)) {
            return componentManager.getInterceptors(names);
        }

        return Collections.emptyList();
    }

    List<Loader<P, R>> getLoaders(ProcessorConfig processorConfig) {
        if (processorConfig != null && CollectionUtils.isNotEmpty(processorConfig.getLoaders())) {
            List<String> names = processorConfig.getLoaders().stream().map(LoaderConfig::getName).collect(Collectors.toList());
            List<Loader<P, R>> loaders = componentManager.getLoaders(names);
            return loaders;
        }
        return Collections.emptyList();
    }

    private R excute(Loader<P, R> loader, P param) {
        R result = null;
        result = loader.load(param);
        if (result != null) {
        }
        return result;
    }
}
