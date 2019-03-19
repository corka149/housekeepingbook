package org.corka.housholdkeepingbook.domain.user;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @SneakyThrows
    public User addUser(User user) {
        if (this.userRepository.findByNameContainingIgnoreCase(user.getName()) != null) throw new UserAlreadyExistsException();
        log.info("New user will be added: {}", user.toString());

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll().stream()
                .filter(user -> !user.getName().equalsIgnoreCase("admin"))
                .collect(Collectors.toList());
    }

    public void deleteUser(long id) {
        log.info("User with id {} will be deleted.");
        this.userRepository.deleteById(id);
    }

    public User findUserByName(String userName) {
        return this.userRepository.findByNameContainingIgnoreCase(userName);
    }

    public User getUserById(long userId) {
        return this.userRepository.getOne(userId);
    }
}
