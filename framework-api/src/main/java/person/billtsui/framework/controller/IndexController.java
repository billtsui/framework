package person.billtsui.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import person.billtsui.framework.services.employee.EmployeeService;
import person.billtsui.framework.services.employee.getemployeeinfo.model.GetEmployeeInfoRequest;
import person.billtsui.framework.services.employee.getemployeeinfo.model.GetEmployeeInfoResponse;

/**
 * @author billtsui
 * @date 2021/3/22
 */
@RestController
public class IndexController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping({"/", "index"})
    public String index() {
        return "Hello World";
    }

    @RequestMapping("emp")
    public GetEmployeeInfoResponse getEmployeeInfo(GetEmployeeInfoRequest request){
        return employeeService.getEmployeeInfo(request);
    }
}
