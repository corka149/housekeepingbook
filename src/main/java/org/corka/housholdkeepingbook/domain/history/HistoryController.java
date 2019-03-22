package org.corka.housholdkeepingbook.domain.history;

import lombok.extern.slf4j.Slf4j;
import org.corka.housholdkeepingbook.misc.Range;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("history")
public class HistoryController {

    @GetMapping
    public String viewHistory(Model model) {
        model.addAttribute("months", Range.getIntegerRange(1, 13));
        return "history";
    }

}
