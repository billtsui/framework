package person.billtsui.framework.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author billtsui
 * @date 2021/3/22
 */
@RestController
public class IndexController {

    @RequestMapping({"/", "index"})
    public String index() {
        return "Hello World";
    }
}
