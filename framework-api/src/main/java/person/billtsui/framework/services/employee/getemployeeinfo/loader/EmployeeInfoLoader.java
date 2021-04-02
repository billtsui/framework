package person.billtsui.framework.services.employee.getemployeeinfo.loader;

import org.springframework.stereotype.Component;
import person.billtsui.framework.module.Loader;
import person.billtsui.framework.services.employee.getemployeeinfo.EmployeeInfoParam;
import person.billtsui.framework.services.employee.getemployeeinfo.model.Employee;
import person.billtsui.framework.services.employee.getemployeeinfo.model.GetEmployeeInfoResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author billtsui
 * @date 2021/3/30
 */
@Component
public class EmployeeInfoLoader implements Loader<EmployeeInfoParam, GetEmployeeInfoResponse> {
    @Override
    public GetEmployeeInfoResponse load(EmployeeInfoParam param) {
        GetEmployeeInfoResponse response = new GetEmployeeInfoResponse();
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Employee employee = new Employee();
            employee.setEmpNo(i);
            employeeList.add(employee);
        }
        response.setEmployeeList(employeeList);
        return response;
    }
}
