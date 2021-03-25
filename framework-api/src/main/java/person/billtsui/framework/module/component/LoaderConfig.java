package person.billtsui.framework.module.component;

import lombok.Data;

import java.util.List;

/**
 * @author billtsui
 * @date 2021/3/22
 */

@Data
public class LoaderConfig {
    private String name;
    private long timeout;
    private List<AppenderConfig> appenders;
}
