package org.corka.housholdkeepingbook.misc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String redirect(Model model) {
        return "index";
    }

}
