package org.corka.housholdkeepingbook.domain.category;

import lombok.var;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest {


    @Test
    public void testEqual() {
        var category1 = new Category("food");
        var category2 = new Category("food");

        assertThat(category1).isEqualTo(category2);
    }

    @Test
    public void testCategoryIsByDefaultNotDeleted() {
        var category = new Category();

        assertThat(category.isDeleted()).isFalse();
    }

    @Test
    public void testHash() {
        var category1 = new Category("food");
        var category2 = new Category("insurance");
        var category3 = new Category("food");

        assertThat(category1.hashCode()).isEqualTo(category3.hashCode());
        assertThat(category1.hashCode()).isNotEqualTo(category2.hashCode());
    }
}
