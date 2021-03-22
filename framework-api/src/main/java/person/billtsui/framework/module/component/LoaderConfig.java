package person.billtsui.framework.module.component;

import java.util.List;

/**
 * @author billtsui
 * @date 2021/3/22
 */
public class LoaderConfig {
    private transient String module;

    private String name;

    private List<String> interceptors;

    private List<AppenderConfig> appenders;

    //超时时间
    private Long timeout;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<String> interceptors) {
        this.interceptors = interceptors;
    }

    public List<AppenderConfig> getAppenders() {
        return appenders;
    }

    public void setAppenders(List<AppenderConfig> appenders) {
        this.appenders = appenders;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }
}
