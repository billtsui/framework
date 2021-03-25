package person.billtsui.framework.module.component.response;

import lombok.Data;

/**
 * @author billtsui
 * @date 2021/3/25
 */
@Data
public class ApiResponseHead {
    private AckCodeType code;
    private String message;
    private Long timestamp;
    private String id;
    private Integer timeUseage;
}
