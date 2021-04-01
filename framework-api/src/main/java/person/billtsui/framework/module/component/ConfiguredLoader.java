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

    public ConfiguredLoader(String module, Loader<P, R> loader) {
        this.loader = loader;
        this.loaderConfig = LoaderConfig.buildDefault(module, loader);
    }

    public ConfiguredLoader(Loader<P, R> loader, LoaderConfig loaderConfig) {
        this.loader = loader;
        this.loaderConfig = loaderConfig;
    }

    public Loader<P, R> getLoader() {
        return loader;
    }

    public void setLoader(Loader<P, R> loader) {
        this.loader = loader;
    }

    public LoaderConfig getLoaderConfig() {
        return loaderConfig;
    }

    public void setLoaderConfig(LoaderConfig loaderConfig) {
        this.loaderConfig = loaderConfig;
    }

    @Override
    public R load(P param) {
        return this.loader.load(param);
    }
}
