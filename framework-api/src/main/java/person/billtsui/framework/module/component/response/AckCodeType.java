package person.billtsui.framework.module.component.response;

/**
 * @author billtsui
 * @date 2021/3/25
 */
public enum AckCodeType {
    //成功
    Success(0,"Success"),
    //失败
    Failure(1,"Failure"),
    //警告
    Warning(2,"Warning");

    private Integer intValue;
    private String value;

    AckCodeType(Integer intValue, String value) {
        this.intValue = intValue;
        this.value = value;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
