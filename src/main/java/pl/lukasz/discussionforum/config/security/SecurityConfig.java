package pl.lukasz.discussionforum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] FOR_AUTHORIZED_USERS =
            {"/user/**", "/test", };
    private static final String[] FOR_ADMINS = {
            "/testical", "testoo"
    };

    private static final String[] AUTHORIZED_ROLES =
            {"USER", "ADMIN"};

    private static final String[] ADMINS_ROLES =
            {"ADMIN"};


    @Autowired
    @Qualifier("userDetailsServiceImple")
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;





    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(FOR_AUTHORIZED_USERS).authenticated()
                .antMatchers(FOR_ADMINS).hasAnyRole(ADMINS_ROLES)
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .rememberMe()
                .tokenValiditySeconds(60)
                .key("forum-key");

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        http.addFilterBefore(characterEncodingFilter, CsrfFilter.class);

    }
}
