package person.billtsui.framework.module.component;


import lombok.Data;

import java.util.List;

/**
 * @author billtsui
 * @date 2021/3/22
 */
@Data
public class ProcessorConfig {
    private String name;
    private List<LoaderConfig> loaders;
    private List<String> interceptors;

}
