package person.billtsui.framework.services.employee.getemployeeinfo.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import person.billtsui.framework.module.Loader;
import person.billtsui.framework.module.component.AbstractModuleServiceProcessor;
import person.billtsui.framework.services.employee.getemployeeinfo.EmployeeInfoParam;
import person.billtsui.framework.services.employee.getemployeeinfo.loader.EmployeeInfoLoader;
import person.billtsui.framework.services.employee.getemployeeinfo.model.GetEmployeeInfoResponse;

/**
 * @author billtsui
 * @date 2021/3/30
 */
@Component
public class EmployeeProcessor extends AbstractModuleServiceProcessor<EmployeeInfoParam, GetEmployeeInfoResponse> {

    @Autowired
    private EmployeeInfoLoader getEmployeeInfoLoader;

    @Override
    protected Loader<EmployeeInfoParam, GetEmployeeInfoResponse> getDefaultLoader() {
        return getEmployeeInfoLoader;
    }
}
