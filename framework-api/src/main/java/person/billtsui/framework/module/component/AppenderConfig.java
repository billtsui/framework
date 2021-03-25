package person.billtsui.framework.module.component;

import lombok.Data;

import java.util.List;

/**
 * @author billtsui
 * @date 2021/3/22
 */
@Data
public class AppenderConfig {
    private String name;
    private boolean async;
    private List<String> interceptors;
    private Long timeout;
}
