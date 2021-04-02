package person.billtsui.framework.module.component.model;

import lombok.Data;
import person.billtsui.framework.module.Appender;

import java.util.List;

/**
 * @author billtsui
 * @date 2021/3/22
 */
public class AppenderConfig{
    private transient String module;
    private String name;
    private boolean async;
    private List<String> interceptors;
    private Long timeout;

    public static AppenderConfig buildDefault(String module, Appender appender) {
        AppenderConfig config = new AppenderConfig();
        config.setModule(module);
        config.setName(appender.getClass().getSimpleName());
        return config;
    }

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

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public List<String> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<String> interceptors) {
        this.interceptors = interceptors;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }
}
