package person.billtsui.framework.module.component;


import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author billtsui
 * @date 2021/3/22
 */
public class ProviderConfig {
    private transient String module;

    private List<LoaderConfig> loaderList;
    private List<String> interceptorList;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
        if (CollectionUtils.isNotEmpty(loaderList)) {
            this.loaderList.forEach(locader -> locader.setModule(module));
        }
    }

    public List<LoaderConfig> getLoaderList() {
        return loaderList;
    }

    public void setLoaderList(List<LoaderConfig> loaderList) {
        this.loaderList = loaderList;
    }

    public List<String> getInterceptorList() {
        return interceptorList;
    }

    public void setInterceptorList(List<String> interceptorList) {
        this.interceptorList = interceptorList;
    }
}
