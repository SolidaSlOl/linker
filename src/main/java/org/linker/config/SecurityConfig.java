package org.linker.config;

import org.linker.model.domain.Role;
import org.linker.repository.springdatajpa.SpringDataRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static String userRole = "USER_ROLE";
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SpringDataRoleRepository roleRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Role role = this.roleRepository.findByName(userRole);
        if (role == null) {
            role = new Role(userRole);
            this.roleRepository.save(role);
        }
        http
            .authorizeRequests()
            .antMatchers("/resources**", "/links/create")
            .hasAnyAuthority(userRole)
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
        throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(
            bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
