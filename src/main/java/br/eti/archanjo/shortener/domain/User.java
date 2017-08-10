package br.eti.archanjo.shortener.domain;

import br.eti.archanjo.shortener.dtos.UserDTO;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class User {
    public UserDTO create() {
        return null;
    }
}
