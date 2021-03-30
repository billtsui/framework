package person.billtsui.framework.services.employee.getemployeeinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import person.billtsui.framework.module.Loader;
import person.billtsui.framework.module.component.AbstractModuleServiceProcessor;
import person.billtsui.framework.services.employee.getemployeeinfo.loader.GetEmployeeInfoLoader;
import person.billtsui.framework.services.employee.getemployeeinfo.model.GetEmployeeInfoResponse;

/**
 * @author billtsui
 * @date 2021/3/30
 */
@Component
public class EmployeeProcessor extends AbstractModuleServiceProcessor<EmployeeInfoParam, GetEmployeeInfoResponse> {
    @Autowired
    GetEmployeeInfoLoader getEmployeeInfoLoader;

    @Override
    protected Loader<EmployeeInfoParam, GetEmployeeInfoResponse> getDefaultLoader() {
        return getEmployeeInfoLoader;
    }
}
