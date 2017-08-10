package br.eti.archanjo.shortener.configs;

import br.eti.archanjo.shortener.constants.ExceptionConstants;
import br.eti.archanjo.shortener.constants.PathConstants;
import br.eti.archanjo.shortener.filters.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    public SecurityConfig(CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/*").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                .failureHandler(
                        (request, response, exception) -> response.sendError(400, ExceptionConstants.PASSWORD_DOES_NOT_MATCH)
                )
                .successHandler(
                        (request, response, authentication) -> response.sendError(200, "logged")
                )
                .loginPage(PathConstants.LOGIN).passwordParameter("password").usernameParameter("username").permitAll()
                .and()
                .logout().logoutUrl(PathConstants.LOGOUT).permitAll()
                .invalidateHttpSession(true)
                .logoutSuccessHandler(
                        (request, response, authentication) -> response.sendError(200, "logout ok")
                )
                .and()
                .exceptionHandling().accessDeniedHandler(customAuthenticationEntryPoint).authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .headers().xssProtection().block(true)
                .and()
                .frameOptions().sameOrigin()
                .and()
                .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(provider);
    }
}