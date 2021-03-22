package person.billtsui.framework.module;

import javax.annotation.Nonnull;

/**
 * @author billtsui
 * @date 2021/3/22
 */
public interface Appender<P, R> {
    /**
     * 处理Loader后面的数据
     * @param param
     * @param result
     */
    void appender(@Nonnull P param, @Nonnull R result);
}
