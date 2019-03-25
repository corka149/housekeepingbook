package org.corka.housholdkeepingbook.domain.history;

import lombok.extern.slf4j.Slf4j;
import org.corka.housholdkeepingbook.misc.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("history")
public class HistoryController {

    private HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping
    public String viewHistory(Model model, @RequestParam(required = false) Integer selectedYear, @RequestParam(required = false) Integer selectedMonth) {
        log.info("selectedYear: {} - selectedMonth: {}", selectedYear, selectedMonth);
        model.addAttribute("months", Range.getIntegerRange(1, 13));
        model.addAttribute("years", Range.getIntegerRange(2015, 2026));

        model.addAttribute("currentMonth", LocalDate.now().getMonthValue());
        model.addAttribute("currentYear", LocalDate.now().getYear());

        if (selectedYear != null && selectedMonth != null)
            model.addAttribute("historicalPayoffs", this.historyService.getActivePayoffsInRange(selectedYear, selectedMonth));
        return "history";
    }

}
