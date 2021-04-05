package com.poppytait.bookingapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.poppytait.bookingapi.constants.SeedUsers.KYLE;
import static com.poppytait.bookingapi.constants.SeedUsers.LINDA;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

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

        http.headers().frameOptions().disable();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails kyleUser = User.builder()
                .username(KYLE.getUsername())
                .password(KYLE.getPassword())
                .authorities(KYLE.getRole().getGrantedAuthorities())
                .build();

        UserDetails lindaUser = User.builder()
                .username(LINDA.getUsername())
                .password(LINDA.getPassword())
                .authorities(LINDA.getRole().getGrantedAuthorities())
                .build();


        return new InMemoryUserDetailsManager(
                kyleUser,
                lindaUser
        );
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }
}
