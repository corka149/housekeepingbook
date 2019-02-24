package org.corka.housholdkeepingbook;

import lombok.extern.slf4j.Slf4j;
import org.corka.housholdkeepingbook.domain.user.User;
import org.corka.housholdkeepingbook.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootApplication
public class ApplicationMain implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("admin");
        user.setPassword("secret");

        userRepository.save(user);
        log.info("Added default user");
    }
}
