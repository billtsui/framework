package person.billtsui.framework.module.component;


import java.util.List;

/**
 * @author billtsui
 * @date 2021/3/22
 */
public class ProcessorConfig {

    private List<LoaderConfig> loaders;
    private List<String> interceptors;

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
