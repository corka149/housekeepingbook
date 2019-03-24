package org.corka.housholdkeepingbook.domain.payoff;

import lombok.val;
import org.corka.housholdkeepingbook.domain.category.Category;
import org.corka.housholdkeepingbook.domain.category.CategoryService;
import org.corka.housholdkeepingbook.domain.user.User;
import org.corka.housholdkeepingbook.domain.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PayoffServiceTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PayoffService payoffService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    User user;
    Category category;

    @Before
    public void setup(){
        deleteFromTables(jdbcTemplate, "payoff");
        deleteFromTables(jdbcTemplate, "category");
        deleteFromTables(jdbcTemplate, "user");

        this.user = new User();
        user.setName("Bob");
        user.setPassword("secret");
        this.userService.addUser(user);

        this.category = this.categoryService.addCategory("insurance", user.getName());
    }

    @Test
    public void testAddPayoff() {
        val payoffDto = new PayoffDto();
        payoffDto.setCategoryId(category.getId());
        payoffDto.setDescription("AU");
        payoffDto.setAmount(30.0f);
        payoffDto.setPayoffDate(LocalDate.now());

        checkRowCount(0);
        val newPayoff = this.payoffService.addPayoff(payoffDto, this.user.getName());
        checkRowCount(1);

        assertThat(newPayoff.getCategoryId()).isEqualTo(this.category.getId());
        assertThat(newPayoff.getCreatorUserId()).isEqualTo(this.user.getId());
        assertThat(newPayoff.getAmount()).isEqualTo(payoffDto.getAmount());
        assertThat(newPayoff.getDescription()).isEqualTo(payoffDto.getDescription());
    }

    @Test
    public void testGetAllActivePayoffs() {

    }

    @Test
    public void testGetLatestPayoff() {

    }

    @Test
    public void testDeletePayoff() {

    }


    private void checkRowCount(int expected) {
        assertThat(countRowsInTable(jdbcTemplate, "payoff")).isEqualTo(expected);
    }
}
