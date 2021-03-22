package person.billtsui.framework.module;

/**
 * @author billtsui
 * @date 2021/3/22
 */
public interface Loader<P,R> {
    /**
     * 加载初始数据
     * @param param
     * @return
     */
    R load(P param);
}
