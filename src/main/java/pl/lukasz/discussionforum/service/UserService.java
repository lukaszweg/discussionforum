package pl.lukasz.discussionforum.service;

import pl.lukasz.discussionforum.entity.User;

public interface UserService {
    User findByUsername(String username);

    void presave(User user);

    User save(User user);
}
