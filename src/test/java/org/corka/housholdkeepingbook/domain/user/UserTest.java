package org.corka.housholdkeepingbook.domain.user;

import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void testIsNotDeleted() {
        val user = new User();
        user.setDeleted(true);
        assertThat(user.isNotDeleted()).isFalse();

        user.setDeleted(false);
        assertThat(user.isNotDeleted()).isTrue();
    }

}
