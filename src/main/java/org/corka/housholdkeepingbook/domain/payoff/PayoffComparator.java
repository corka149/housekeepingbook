package org.corka.housholdkeepingbook.domain.payoff;

import java.util.Comparator;

public class PayoffComparator implements Comparator<Payoff> {

    @Override
    public int compare(Payoff payoff, Payoff otherPayoff) {
        return (int) payoff.getPayoffDate().toEpochDay() - (int) otherPayoff.getPayoffDate().toEpochDay();
    }
}
