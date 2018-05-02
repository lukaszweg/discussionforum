package pl.lukasz.discussionforum.service;

import pl.lukasz.discussionforum.entity.Role;

public interface RoleService {
    void save(Role role);
    Role findByName(String name);
}
