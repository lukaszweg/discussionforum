package pl.lukasz.discussionforum.service.implementation;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.lukasz.discussionforum.entity.Role;
import pl.lukasz.discussionforum.entity.User;
import pl.lukasz.discussionforum.repository.UserRepository;
import pl.lukasz.discussionforum.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsServiceImple")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for(Role role: user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),true,true,true,true,grantedAuthorities);
        return userDetails;
    }
}
