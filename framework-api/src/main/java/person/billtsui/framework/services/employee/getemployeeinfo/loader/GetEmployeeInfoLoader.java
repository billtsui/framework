package person.billtsui.framework.services.employee.getemployeeinfo.loader;

import org.springframework.stereotype.Component;
import person.billtsui.framework.module.Loader;
import person.billtsui.framework.services.employee.getemployeeinfo.EmployeeInfoParam;
import person.billtsui.framework.services.employee.getemployeeinfo.model.GetEmployeeInfoResponse;

/**
 * @author billtsui
 * @date 2021/3/30
 */
@Component
public class GetEmployeeInfoLoader implements Loader<EmployeeInfoParam, GetEmployeeInfoResponse> {
    @Override
    public GetEmployeeInfoResponse load(EmployeeInfoParam param) {
        return null;
    }
}
