package org.corka.housholdkeepingbook.domain.payoff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayoffRepository extends JpaRepository<Payoff, Long> {
}
