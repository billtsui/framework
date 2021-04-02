package person.billtsui.framework.module.component;


import person.billtsui.framework.module.Appender;
import person.billtsui.framework.module.component.model.AppenderConfig;

/**
 * @author billtsui
 * @date 2021/3/31
 */
public class ConfiguredAppender<P, R> {
    private Appender<P, R> appender;
    private AppenderConfig appenderConfig;

    public ConfiguredAppender(String module, Appender<P, R> appender) {
        this.appender = appender;
        this.appenderConfig = AppenderConfig.buildDefault(module, appender);
    }

    public ConfiguredAppender(Appender<P, R> appender, AppenderConfig appenderConfig) {
        this.appender = appender;
        this.appenderConfig = appenderConfig;
    }

    public void append(P param, R result) {
        this.appender.appender(param, result);
    }

    public AppenderConfig getAppenderConfig() {
        return appenderConfig;
    }
}
