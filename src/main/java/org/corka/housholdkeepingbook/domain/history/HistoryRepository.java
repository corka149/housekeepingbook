package org.corka.housholdkeepingbook.domain.history;

import org.corka.housholdkeepingbook.domain.payoff.Payoff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface HistoryRepository extends JpaRepository<Payoff, Long> {

    List<Payoff> findByPayoffDateBetween(LocalDate from, LocalDate to);

}
