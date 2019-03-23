package org.corka.housholdkeepingbook.domain.user;

import lombok.val;
import lombok.var;
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
public class UserServiceTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserService userService;

    @Before
    public void setup(){
        deleteFromTables(jdbcTemplate, "payoff");
        deleteFromTables(jdbcTemplate, "category");
        deleteFromTables(jdbcTemplate, "user");
    }

    @Test
    public void testAddUser() {
        checkRowCount(0);

        val user = new User();
        user.setName("Alice");
        user.setPassword("secret");
        user.setFavoriteColor("red");

        this.userService.addUser(user);

        checkRowCount(1);
    }

    @Test
    public void testGetAllUser() {
        checkRowCount(0);

        val user1 = new User();
        user1.setName("Alice");
        user1.setPassword("secret");
        user1.setFavoriteColor("red");
        this.userService.addUser(user1);

        val user2 = new User();
        user2.setName("Bob");
        user2.setPassword("secret");
        user2.setFavoriteColor("green");
        this.userService.addUser(user2);

        val user3 = new User();
        user3.setName("Ceaser");
        user3.setPassword("secret");
        user3.setFavoriteColor("orange");
        this.userService.addUser(user3);

        checkRowCount(3);

        assertThat(this.userService.getAllActiveUsers().size()).isEqualTo(3);
    }

    @Test
    public void testDeleteUser() {
        checkRowCount(0);

        val user1 = new User();
        user1.setName("Alice");
        user1.setPassword("secret");
        user1.setFavoriteColor("red");
        this.userService.addUser(user1);

        val user2 = new User();
        user2.setName("Bob");
        user2.setPassword("secret");
        user2.setFavoriteColor("green");
        this.userService.addUser(user2);

        val user3 = new User();
        user3.setName("Ceaser");
        user3.setPassword("secret");
        user3.setFavoriteColor("orange");
        this.userService.addUser(user3);

        var activeUsers = this.userService.getAllActiveUsers();

        this.userService.deleteUser(user2.getId());
        checkRowCount(3); // Deletes only logical

        assertThat(this.userService.getAllActiveUsers().size()).isEqualTo(2);
    }

    @Test
    public void testFindUserByName() {
        checkRowCount(0);

        val user1 = new User();
        user1.setName("Alice");
        user1.setPassword("secret");
        user1.setFavoriteColor("red");
        this.userService.addUser(user1);

        val user2 = new User();
        user2.setName("Bob");
        user2.setPassword("secret");
        user2.setFavoriteColor("green");
        this.userService.addUser(user2);

        val user3 = new User();
        user3.setName("Ceaser");
        user3.setPassword("secret");
        user3.setFavoriteColor("orange");
        this.userService.addUser(user3);

        checkRowCount(3);

        assertThat(this.userService.findUserByNameIgnoreCase("bob")).isEqualTo(user2);
    }


    private void checkRowCount(int expected) {
        assertThat(countRowsInTable(jdbcTemplate, "user")).isEqualTo(expected);
    }
}
