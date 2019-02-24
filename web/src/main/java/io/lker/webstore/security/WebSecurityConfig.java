package io.lker.webstore.security;

import io.lker.webstore.usermanagement.model.security.TokenHelper;
import io.lker.webstore.usermanagement.services.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserAuthService userAuthService;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final TokenHelper tokenHelper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(UserAuthService userAuthService, RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                             TokenHelper tokenHelper,
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userAuthService = userAuthService;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.tokenHelper = tokenHelper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userAuthService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .authorizeRequests()
                .antMatchers("/h2-console/**/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(new TokenAuthenticationFilter(tokenHelper, userAuthService), BasicAuthenticationFilter.class);
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/h2-console/**/**");
    }
}