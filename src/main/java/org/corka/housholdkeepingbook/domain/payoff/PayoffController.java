package org.corka.housholdkeepingbook.domain.payoff;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("payoff")
public class PayoffController {

    @GetMapping
    public String viewPayoffForm(Model model) {
        return "payoff";
    }

}
