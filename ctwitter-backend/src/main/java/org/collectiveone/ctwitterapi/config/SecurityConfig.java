package org.collectiveone.ctwitterapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${auth0.issuer}")
    private String issuer;

    @Value("${auth0.audience}")
    private String audience;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer.forRS256(audience, issuer)
                .configure(http)
                .authorizeRequests()
                .antMatchers("/1/secure/**").fullyAuthenticated();
        //                .antMatchers(HttpMethod.GET, "/secure/**").hasAuthority("read:greeting");
    }
}