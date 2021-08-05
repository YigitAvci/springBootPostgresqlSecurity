package com.hunter.springbootpostgresql.core.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/users/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMINTRAINEE.name()) //the users who has user role cannot reach this api endpoint
                .antMatchers(HttpMethod.POST, "/api/users/**").hasAuthority(ApplicationUserPermission.USER_WRITE.name())
                .antMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder().username("yigit").password(passwordEncoder.encode("aaaa")).roles(ApplicationUserRole.ADMIN.name()).authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities()).build();

        UserDetails user2 = User.builder().username("mirac").password(passwordEncoder.encode("bbbb")).roles(ApplicationUserRole.USER.name()).authorities(ApplicationUserRole.USER.getGrantedAuthorities()).build();

        UserDetails user3 = User.builder().username("bahadir").password(passwordEncoder.encode("cccc")).roles(ApplicationUserRole.ADMINTRAINEE.name()).authorities(ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities()).build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
}
