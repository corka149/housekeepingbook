package org.corka.housholdkeepingbook.domain.history;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.corka.housholdkeepingbook.domain.payoff.Payoff;
import org.corka.housholdkeepingbook.domain.payoff.PayoffComparator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HistoryService {

    private HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    List<Payoff> getActivePayoffsInRange(int year, int month) {
        val start = LocalDate.of(year, month, 1);
        val end = LocalDate.of(year, month, 1).plusMonths(1);
        val historicalPayoffs = this.historyRepository.findByPayoffDateBetween(start, end).stream()
                .filter(Payoff::isNotDeleted)
                .sorted(new PayoffComparator())
                .collect(Collectors.toList());

        log.info("Return {} for {}/{}", historicalPayoffs.size(), month, year);
        return historicalPayoffs;
    }

}
