package person.billtsui.framework.module.component;


import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author billtsui
 * @date 2021/3/22
 */
public class ProcessorConfig {
    private transient String module;

    private List<LoaderConfig> loaders;
    private List<String> interceptors;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
        if (CollectionUtils.isNotEmpty(loaders)) {
            this.loaders.forEach(locader -> locader.setModule(module));
        }
    }

    public List<LoaderConfig> getLoaders() {
        return loaders;
    }

    public void setLoaders(List<LoaderConfig> loaders) {
        this.loaders = loaders;
    }

    public List<String> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<String> interceptors) {
        this.interceptors = interceptors;
    }
}
