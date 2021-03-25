package person.billtsui.framework.module.component;

/**
 * @author billtsui
 * @date 2021/3/23
 */
public interface ModuleServiceProcessor<P,R> {
    R handle(P param);
}
