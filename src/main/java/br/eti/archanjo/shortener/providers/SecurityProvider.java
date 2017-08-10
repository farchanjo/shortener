package br.eti.archanjo.shortener.providers;

import br.eti.archanjo.shortener.constants.ExceptionConstants;
import br.eti.archanjo.shortener.domain.User;
import br.eti.archanjo.shortener.entities.mysql.UserEntity;
import br.eti.archanjo.shortener.exceptions.NotFoundException;
import br.eti.archanjo.shortener.utils.parsers.UserParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SecurityProvider implements AuthenticationProvider {
    private static Logger logger = LoggerFactory.getLogger(SecurityProvider.class);

    private final User user;

    @Autowired
    public SecurityProvider(User user) {
        this.user = user;
    }

    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;

        UserEntity entity;
        try {
            entity = user.authenticate(String.valueOf(auth.getPrincipal()), String.valueOf(auth.getCredentials()));

            if (entity == null) {
                throw new NotFoundException(ExceptionConstants.USER_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new BadCredentialsException(ExceptionConstants.PASSWORD_DOES_NOT_MATCH);
        }

        return new UsernamePasswordAuthenticationToken(UserParser.toDTO(entity), null, Collections.singletonList(new SimpleGrantedAuthority(entity.getRoles().name())));
    }

    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}