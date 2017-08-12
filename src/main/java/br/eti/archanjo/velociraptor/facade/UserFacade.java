package br.eti.archanjo.velociraptor.facade;

import br.eti.archanjo.velociraptor.domain.User;
import br.eti.archanjo.velociraptor.dtos.UserDTO;
import br.eti.archanjo.velociraptor.enums.Roles;
import br.eti.archanjo.velociraptor.enums.Status;
import br.eti.archanjo.velociraptor.exceptions.MissingArgumentException;
import br.eti.archanjo.velociraptor.exceptions.NotAuthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserFacade {
    private final User user;

    @Autowired
    public UserFacade(User user) {
        this.user = user;
    }

    public UserDTO create(UserDTO userDTO, UserDTO client) throws Exception {
        if (userDTO.getPassword() == null && userDTO.getId() == null)
            throw new MissingArgumentException("Missing password");

        if (!client.getRoles().equals(Roles.ADMIN))
            throw new NotAuthorizedException("You cannot list all users. You dont have permission for that");
        return user.create(userDTO);
    }

    /**
     * @param client {@link UserDTO}
     * @return {@link UserDTO}
     * @throws Exception
     */
    public UserDTO me(UserDTO client) throws Exception {
        return user.me(client);
    }

    /**
     * @param page   {@link Integer}
     * @param size   {@link Integer}
     * @param status {@link Status}
     * @param client {@link UserDTO}
     * @return {@link Page<UserDTO>}
     */
    public Page<UserDTO> listUsers(Integer page, Integer size, Status status, UserDTO client) {
        if (!client.getRoles().equals(Roles.ADMIN))
            throw new NotAuthorizedException("You cannot list all users. You dont have permission for that");
        return user.listUsers(page, size, status);
    }
}
