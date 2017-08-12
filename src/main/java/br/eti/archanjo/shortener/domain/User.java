package br.eti.archanjo.shortener.domain;

import br.eti.archanjo.shortener.dtos.UserDTO;
import br.eti.archanjo.shortener.entities.mysql.UserEntity;
import br.eti.archanjo.shortener.enums.Status;
import br.eti.archanjo.shortener.exceptions.NotFoundException;
import br.eti.archanjo.shortener.repositories.mysql.UserRepository;
import br.eti.archanjo.shortener.utils.HashUtils;
import br.eti.archanjo.shortener.utils.parsers.UserParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class User {

    private final UserRepository userRepository;

    @Autowired
    public User(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param userDTO {@link UserDTO}
     * @return {@link UserDTO}
     */
    public UserDTO create(UserDTO userDTO) {
        UserEntity user;
        if (userDTO.getId() != null) {
            user = userRepository.findOne(userDTO.getId());
            user.setName(userDTO.getName());
            user.setRoles(userDTO.getRoles());
            user.setEmail(userDTO.getEmail());
            user.setPassword(HashUtils.sha256(userDTO.getPassword()));
            user.setStatus(userDTO.getStatus());
            user.setUsername(userDTO.getUsername());
        } else {
            user = UserEntity.builder()
                    .email(userDTO.getEmail())
                    .name(userDTO.getName())
                    .roles(userDTO.getRoles())
                    .status(userDTO.getStatus())
                    .password(HashUtils.sha256(userDTO.getPassword()))
                    .username(userDTO.getUsername())
                    .build();
        }
        user = userRepository.save(user);
        return UserParser.toDTO(user);
    }

    /**
     * @param user     {@link String}
     * @param password {@link String}
     * @return {@link UserEntity}
     */
    public UserEntity authenticate(String user, String password) {
        return userRepository.findByUsernameAndPasswordAndStatus(user, HashUtils.sha256(password), Status.ENABLED);
    }

    /**
     * @param client {@link UserDTO}
     * @return {@link UserDTO}
     * @throws NotFoundException
     */
    public UserDTO me(UserDTO client) throws NotFoundException {
        UserEntity user = userRepository.findOne(client.getId());
        if (user == null)
            throw new NotFoundException("This user does not exist anymore");
        return UserParser.toDTO(user);
    }

    /**
     * @param limit {@link Integer}
     * @param size  {@link Integer}
     * @return {@link Page<UserDTO>}
     */
    public Page<UserDTO> listUsers(Integer page, Integer size) {
        Page<UserEntity> users = userRepository.findAll(new PageRequest(page, size));
        return users.map(UserParser::toDTO);
    }
}
