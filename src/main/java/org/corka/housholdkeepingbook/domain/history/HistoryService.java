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

    double getSumOfRegularPayoffs(int year, int month) {
        val sumOfRegularPayoffs =  this.getActivePayoffsInRange(year, month).stream()
                .filter(Payoff::isRegularOccurrence)
                .mapToDouble(Payoff::getAmount).sum();
        log.info("sumOfRegularPayoffs of year {} and month {} is {}.", year, month, sumOfRegularPayoffs);
        return sumOfRegularPayoffs;
    }

    double getSumOfIrregularPayoffs(int year, int month) {
        val sumOfIrregularPayoffs = this.getActivePayoffsInRange(year, month).stream()
                .filter(Payoff::isIrregular)
                .mapToDouble(Payoff::getAmount)
                .sum();
        log.info("sumOfIrregularPayoffs of year {} and month {} is {}.", year, month, sumOfIrregularPayoffs);
        return sumOfIrregularPayoffs;
    }

    double getSumOfPayoffs(int year, int month) {
        val sumOfPayoffs = this.getActivePayoffsInRange(year, month).stream()
                .mapToDouble(Payoff::getAmount)
                .sum();
        log.info("sumOfPayoffs of year {} and month {} is {}.", year, month, sumOfPayoffs);
        return sumOfPayoffs;
    }
}
