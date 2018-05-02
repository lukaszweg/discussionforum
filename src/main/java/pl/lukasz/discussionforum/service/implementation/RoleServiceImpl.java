package pl.lukasz.discussionforum.service.implementation;

import org.springframework.stereotype.Service;
import pl.lukasz.discussionforum.entity.Role;
import pl.lukasz.discussionforum.repository.RoleRepository;
import pl.lukasz.discussionforum.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void save(Role role) {
     roleRepository.save(role);
    }

    @Override
    public Role findByName(String name) {
       Role role =  roleRepository.findByName(name);
       return role;
    }
}
