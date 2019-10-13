package org.corka.housholdkeepingbook.domain.payoff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PayoffRepository extends JpaRepository<Payoff, Long> {

    @Query("select p from Payoff p where deleted = FALSE order by creationDate desc")
    List<Payoff> findLatestAddedActivePayoffs();

    @Query("select max(p.id) from Payoff p")
    Optional<Long> findHighestPayOffId();
}
