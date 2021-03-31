package person.billtsui.framework.module.component.model;

import java.util.List;

/**
 * @author billtsui
 * @date 2021/3/22
 */

public class LoaderConfig{
    private String name;
    private long timeout;
    private List<AppenderConfig> appenders;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public List<AppenderConfig> getAppenders() {
        return appenders;
    }

    public void setAppenders(List<AppenderConfig> appenders) {
        this.appenders = appenders;
    }
}
