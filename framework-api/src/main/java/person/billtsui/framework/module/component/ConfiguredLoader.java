package person.billtsui.framework.module.component;

import person.billtsui.framework.module.Loader;
import person.billtsui.framework.module.component.model.LoaderConfig;

/**
 * @author billtsui
 * @date 2021/3/31
 */
public class ConfiguredLoader<P, R> implements Loader<P, R> {

    private Loader<P, R> loader;

    private LoaderConfig loaderConfig;

    @Override
    public R load(P param) {
        return this.loader.load(param);
    }
}
