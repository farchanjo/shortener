package br.eti.archanjo.velociraptor.providers;

import br.eti.archanjo.velociraptor.constants.ExceptionConstants;
import br.eti.archanjo.velociraptor.domain.User;
import br.eti.archanjo.velociraptor.dtos.UserDTO;
import br.eti.archanjo.velociraptor.entities.mysql.UserEntity;
import br.eti.archanjo.velociraptor.exceptions.NotFoundException;
import org.dozer.DozerBeanMapper;
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

    private final DozerBeanMapper mapper;

    @Autowired
    public SecurityProvider(User user, DozerBeanMapper mapper) {
        this.user = user;
        this.mapper = mapper;
    }

    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        UserEntity entity;
        try {
            entity = user.authenticate(String.valueOf(authentication.getPrincipal()), String.valueOf(authentication.getCredentials()));

            if (entity == null) {
                throw new NotFoundException(ExceptionConstants.USER_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new BadCredentialsException(ExceptionConstants.PASSWORD_DOES_NOT_MATCH);
        }

        return new UsernamePasswordAuthenticationToken(mapper.map(entity, UserDTO.class), null, Collections.singletonList(new SimpleGrantedAuthority(entity.getRoles().name())));
    }

    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}