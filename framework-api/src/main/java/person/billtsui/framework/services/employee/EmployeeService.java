package person.billtsui.framework.services.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import person.billtsui.framework.services.employee.getemployeeinfo.EmployeeProcessor;

/**
 * @author billtsui
 * @date 2021/3/25
 */
@Service
public class EmployeeService {
    @Autowired
    EmployeeProcessor employeeProcessor;

    public void test(){
    }
}
