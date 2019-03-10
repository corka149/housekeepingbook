package org.corka.housholdkeepingbook.domain.category;

import lombok.val;
import org.corka.housholdkeepingbook.domain.user.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest {

    private User user;

    @Before
    public void setUp() {
        this.user = new User();
        user.setName("admin");
    }

    @Test
    public void testEqual() {
        val now = LocalDateTime.now();
        val category1 = new Category("food", this.user, now);
        val category2 = new Category("food", this.user, now);

        assertThat(category1).isEqualTo(category2);
    }

    @Test
    public void testCategoryIsByDefaultNotDeleted() {
        val category = new Category();

        assertThat(category.isDeleted()).isFalse();
    }

    @Test
    public void testHash() {
        val now = LocalDateTime.now();
        val category1 = new Category("food", this.user, now);
        val category2 = new Category("insurance", this.user, now);
        val category3 = new Category("food", this.user, now);

        assertThat(category1.hashCode()).isEqualTo(category3.hashCode());
        assertThat(category1.hashCode()).isNotEqualTo(category2.hashCode());
    }
}
