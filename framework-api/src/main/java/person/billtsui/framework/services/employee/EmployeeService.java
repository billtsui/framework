package person.billtsui.framework.services.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import person.billtsui.framework.services.employee.getemployeeinfo.EmployeeInfoParam;
import person.billtsui.framework.services.employee.getemployeeinfo.model.GetEmployeeInfoRequest;
import person.billtsui.framework.services.employee.getemployeeinfo.model.GetEmployeeInfoResponse;
import person.billtsui.framework.services.employee.getemployeeinfo.processor.EmployeeProcessor;

/**
 * @author billtsui
 * @date 2021/3/25
 */
@Service
public class EmployeeService {
    @Autowired
    EmployeeProcessor employeeProcessor;

    public GetEmployeeInfoResponse getEmployeeInfo (GetEmployeeInfoRequest request){
        EmployeeInfoParam employeeInfoParam = new EmployeeInfoParam();
        employeeInfoParam.setModule("Employee");

        return employeeProcessor.handle(employeeInfoParam);
    }
}
