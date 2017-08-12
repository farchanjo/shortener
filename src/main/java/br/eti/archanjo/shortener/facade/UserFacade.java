package br.eti.archanjo.shortener.facade;

import br.eti.archanjo.shortener.domain.User;
import br.eti.archanjo.shortener.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserFacade {
    private final User user;

    @Autowired
    public UserFacade(User user) {
        this.user = user;
    }

    public UserDTO create(UserDTO client) {
        return user.create();
    }

    /**
     * @param client {@link UserDTO}
     * @return {@link UserDTO}
     * @throws Exception
     */
    public UserDTO me(UserDTO client) throws Exception {
        return user.me(client);
    }
}
