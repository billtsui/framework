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

    public void append(P param, R result) {
        this.appender.appender(param, result);
    }

}
