package com.poppytait.bookingapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.poppytait.bookingapi.security.ApplicationUserRole.CUSTOMER;
import static com.poppytait.bookingapi.security.ApplicationUserRole.INSTRUCTOR;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
/*              .antMatchers(HttpMethod.DELETE, "/classes/**").hasAuthority(FITNESS_CLASS_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/classes/**").hasAuthority(FITNESS_CLASS_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/classes/**").hasAuthority(FITNESS_CLASS_READ.getPermission())*/
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails beyonceKnowlesUser = User.builder()
                .username("beyonceknowles")
                .password(passwordEncoder.encode("password"))
               // .roles(CUSTOMER.name())
                .authorities(CUSTOMER.getGrantedAuthorities())
                .build();

        UserDetails lindaUser = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password"))
              // .roles(INSTRUCTOR.name())
                .authorities(INSTRUCTOR.getGrantedAuthorities())

                .build();


        return new InMemoryUserDetailsManager(
                beyonceKnowlesUser,
                lindaUser
        );
    }
}
