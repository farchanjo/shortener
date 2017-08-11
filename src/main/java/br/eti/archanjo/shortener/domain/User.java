package br.eti.archanjo.shortener.domain;

import br.eti.archanjo.shortener.dtos.UserDTO;
import br.eti.archanjo.shortener.entities.mysql.UserEntity;
import br.eti.archanjo.shortener.enums.Status;
import br.eti.archanjo.shortener.repositories.mysql.UserRepository;
import br.eti.archanjo.shortener.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class User {

    private final UserRepository userRepository;

    @Autowired
    public User(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO create() {
        return null;
    }

    /**
     * @param user     {@link String}
     * @param password {@link String}
     * @return {@link UserEntity}
     */
    public UserEntity authenticate(String user, String password) {
        return userRepository.findByUsernameAndPasswordAndStatus(user, HashUtils.sha256(password), Status.ENABLED);
    }
}
