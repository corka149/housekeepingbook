package org.corka.housholdkeepingbook.domain.payoff;

import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PayoffTest {

    @Test
    public void testIsNotDeleted() {
        val payoff = new Payoff();
        payoff.setDeleted(true);
        assertThat(payoff.isNotDeleted()).isFalse();

        payoff.setDeleted(false);
        assertThat(payoff.isNotDeleted()).isTrue();
    }

}
