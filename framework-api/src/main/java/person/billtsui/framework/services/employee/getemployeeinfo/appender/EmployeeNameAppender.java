package person.billtsui.framework.services.employee.getemployeeinfo.appender;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import person.billtsui.framework.module.Appender;
import person.billtsui.framework.services.employee.getemployeeinfo.EmployeeInfoParam;
import person.billtsui.framework.services.employee.getemployeeinfo.model.GetEmployeeInfoResponse;

import javax.annotation.Nonnull;

/**
 * @author billtsui
 * @date 2021/4/2
 */
@Component
public class EmployeeNameAppender implements Appender<EmployeeInfoParam, GetEmployeeInfoResponse> {
    @Override
    public void appender(@Nonnull EmployeeInfoParam param, @Nonnull GetEmployeeInfoResponse result) {
        if (result != null && CollectionUtils.isNotEmpty(result.getEmployeeList())) {
            for (int i = 0; i < result.getEmployeeList().size(); i++) {
                result.getEmployeeList().get(i).setName("name" + i);
            }
        }
    }
}
