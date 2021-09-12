package com.hunter.springbootpostgresql.core.security;

import com.hunter.springbootpostgresql.core.jwt.JwtConfig;
import com.hunter.springbootpostgresql.core.jwt.JwtSecretKey;
import com.hunter.springbootpostgresql.core.jwt.JwtTokenVerifier;
import com.hunter.springbootpostgresql.core.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.crypto.SecretKey;
import java.util.concurrent.TimeUnit;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Autowired
    public ApplicationSecurityConfig(UserDetailsService userDetailsService, SecretKey secretKey, JwtConfig jwtConfig) {
        this.userDetailsService = userDetailsService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }


    /*
    private final PasswordEncoder passwordEncoder;
    private final UserManager userManager;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, UserManager userManager) {
        this.passwordEncoder = passwordEncoder;
        this.userManager = userManager;
    }

     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //you can use this if you are using remember me, form login and some permission
        /*
        http
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) //csrf must be use when you develop a service that is being used by browser-users, otherwise it is not necessary
                //.and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers( HttpMethod.GET, "/api/users/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMINTRAINEE.name())
                .antMatchers(HttpMethod.POST, "/api/users/**").hasAuthority(ApplicationUserPermission.USER_WRITE.getPermission()) //constraint using permissions
                .antMatchers("/api/products/**").hasAnyRole(ApplicationUserRole.USER.name(), ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMINTRAINEE.name()) //constraint using roles
                .anyRequest()
                .authenticated()
                .and()
                //.httpBasic(); //
                .formLogin() //form based authentication
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/main",true) //redirect to a page
                    .passwordParameter("password")
                    .usernameParameter("username")
                .and()
                .rememberMe() //defaults to 2 weeks
                    .rememberMeParameter("remember-me")
                    .tokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(1)) //extends 2 weeks to how long you want
                    .key("somethingVerySecured")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login");
        */


        //you can use this if you are using roles, form login, remember me,
        /*
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/products/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/users/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/main", true)
                .passwordParameter("password").usernameParameter("username")
                .and()
                .rememberMe().rememberMeParameter("remember-me")
                .tokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(10)).key("somethingVerySecured")
                .and()
                .logout().logoutUrl("/logout").logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST")).clearAuthentication(true).deleteCookies("remember-me", "JSESSIONID").logoutSuccessUrl("/login");


         */

        //you can use this if you are using jwt and some constraints related with roles
        http.csrf().disable()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/applicationUsers/**").permitAll()
                .antMatchers("/api/products/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/users/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
