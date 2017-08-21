package br.eti.archanjo.velociraptor.domain;

import br.eti.archanjo.velociraptor.dtos.UserDTO;
import br.eti.archanjo.velociraptor.entities.mysql.UserEntity;
import br.eti.archanjo.velociraptor.enums.Status;
import br.eti.archanjo.velociraptor.exceptions.NotFoundException;
import br.eti.archanjo.velociraptor.repositories.mysql.UserRepository;
import br.eti.archanjo.velociraptor.utils.HashUtils;
import org.dozer.DozerBeanMapper;
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

    private final DozerBeanMapper mapper;

    @Autowired
    public User(UserRepository userRepository, DozerBeanMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
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
                    .username(userDTO.getUsername())
                    .build();
            if (userDTO.getPassword() != null)
                user.setPassword(HashUtils.sha256(userDTO.getPassword()));
        }
        user = userRepository.save(user);
        return mapper.map(user, UserDTO.class);
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
        return mapper.map(user, UserDTO.class);
    }

    /**
     * @param limit  {@link Integer}
     * @param size   {@link Integer}
     * @param status {@link Status}
     * @return {@link Page<UserDTO>}
     */
    public Page<UserDTO> listUsers(Integer page, Integer size, Status status) {
        Page<UserEntity> users = userRepository.findAllByStatus(new PageRequest(page, size), status);
        return users.map(source -> mapper.map(source, UserDTO.class));
    }
}
