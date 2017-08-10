package br.eti.archanjo.shortener.domain;

import br.eti.archanjo.shortener.dtos.UserDTO;
import br.eti.archanjo.shortener.entities.mysql.UserEntity;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class User {
    public UserDTO create() {
        return null;
    }

    /**
     * @param user     {@link String}
     * @param password {@link String}
     * @return {@link UserEntity}
     */
    public UserEntity authenticate(String user, String password) {
        return null;
    }
}
