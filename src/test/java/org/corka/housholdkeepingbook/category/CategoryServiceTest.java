package org.corka.housholdkeepingbook.category;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.jdbc.JdbcTestUtils.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup(){
        deleteFromTables(jdbcTemplate, "category");
    }

    @Test
    public void testAddOneCategoryByString() {
        checkRowCount(0);
        this.categoryService.addCategory("food");
        checkRowCount(1);
    }

    @Test
    public void testAddThreeCategoriesByString() {
        checkRowCount(0);
        this.categoryService.addCategory("food");
        this.categoryService.addCategory("insurance");
        this.categoryService.addCategory("entertainment");
        checkRowCount(3);
    }

    private void checkRowCount(int expected) {
        assertThat(countRowsInTable(jdbcTemplate, "category")).isEqualTo(expected);
    }
}
