package person.billtsui.framework.services.employee.getemployeeinfo.model;

import person.billtsui.framework.module.component.response.ApiResponse;
import person.billtsui.framework.services.employee.getemployeeinfo.model.Employee;

import java.util.List;

/**
 * @author billtsui
 * @date 2021/3/30
 */

public class GetEmployeeInfoResponse extends ApiResponse {
    private List<Employee> employeeList;
}
