package pl.lukasz.discussionforum.service;

import org.springframework.security.core.Authentication;
import pl.lukasz.discussionforum.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findByUsername(String username);

    void presave(User user);

    void presaveEdited(User user);

    User save(User user);

    List<User> findAllUsers();

    void delete(Long id);

    Optional<User> findOneUserById(Long id);



}
