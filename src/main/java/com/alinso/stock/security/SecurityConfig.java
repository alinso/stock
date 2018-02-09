package com.alinso.stock.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{



    @Autowired
    CustomSuccessHandler customSuccessHandler;


    @Autowired
    @Qualifier("SecurityUserService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder  =  new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }


    protected void configure(HttpSecurity http) throws Exception {


        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);



        http.authorizeRequests().antMatchers("/register").permitAll();
        http.authorizeRequests().antMatchers("/user/**").hasRole("USER");
                http.formLogin()
                .failureUrl("/login/error")
                .successHandler(customSuccessHandler);

        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        //remember me configuration
      /*  http.rememberMe().
                key("rem-me-key").
                tokenValiditySeconds(86400);*/





    }
}
