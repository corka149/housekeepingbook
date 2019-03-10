package org.corka.housholdkeepingbook.domain.category;

import lombok.val;
import lombok.var;
import org.corka.housholdkeepingbook.domain.user.User;
import org.corka.housholdkeepingbook.domain.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup(){
        deleteFromTables(jdbcTemplate, "category");

        if (this.userRepository.findByNameContainingIgnoreCase("bob") == null) {
            val user = new User();
            user.setName("Bob");
            user.setPassword("secret");
            this.userRepository.save(user);
        }
    }

    @Test
    public void testAddOneCategoryByString() {
        checkRowCount(0);
        this.categoryService.addCategory("food", "Bob");
        checkRowCount(1);
    }

    @Test
    public void testAddThreeCategoriesByString() {
        checkRowCount(0);
        this.categoryService.addCategory("food", "Bob");
        this.categoryService.addCategory("insurance", "Bob");
        this.categoryService.addCategory("entertainment", "Bob");
        checkRowCount(3);
    }

    @Test
    public void testGetAllActiveCategories() {
        checkRowCount(0);
        this.categoryService.addCategory("food", "Bob");
        this.categoryService.addCategory("insurance", "Bob");
        this.categoryService.addCategory("entertainment", "Bob");

        var activeCategories = this.categoryService.getAllActiveCategories();
        assertThat(activeCategories.size()).isEqualTo(3);

        this.categoryService.deleteCategory(activeCategories.get(0).getId());
        activeCategories = this.categoryService.getAllActiveCategories();
        assertThat(activeCategories.size()).isEqualTo(2);
    }

    private void checkRowCount(int expected) {
        assertThat(countRowsInTable(jdbcTemplate, "category")).isEqualTo(expected);
    }
}
