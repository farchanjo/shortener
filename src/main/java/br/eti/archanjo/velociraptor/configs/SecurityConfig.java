package br.eti.archanjo.velociraptor.configs;

import br.eti.archanjo.velociraptor.constants.ExceptionConstants;
import br.eti.archanjo.velociraptor.constants.PathConstants;
import br.eti.archanjo.velociraptor.filters.CustomAuthenticationEntryPoint;
import br.eti.archanjo.velociraptor.providers.SecurityProvider;
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

    private final SecurityProvider provider;

    @Autowired
    public SecurityConfig(CustomAuthenticationEntryPoint customAuthenticationEntryPoint, SecurityProvider provider) {
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.provider = provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").fullyAuthenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .failureHandler(
                        (request, response, exception) -> response.sendError(400, ExceptionConstants.PASSWORD_DOES_NOT_MATCH)
                )
                .successHandler(
                        (request, response, authentication) -> response.sendError(200, "logged")
                )
                .loginPage(PathConstants.API + PathConstants.LOGIN).passwordParameter("password").usernameParameter("username").permitAll()
                .and()
                .logout().logoutUrl(PathConstants.API + PathConstants.LOGOUT).permitAll()
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
        auth.authenticationProvider(provider);
    }
}