package br.eti.archanjo.shortener.utils.parsers;

import br.eti.archanjo.shortener.dtos.UserDTO;
import br.eti.archanjo.shortener.entities.mysql.UserEntity;

public class UserParser {

    /**
     * @param entity {@link UserEntity}
     * @return {@link UserDTO}
     */
    public static UserDTO toDTO(UserEntity entity) {
        return UserDTO.builder()
                .username(entity.getUsername())
                .status(entity.getStatus())
                .name(entity.getName())
                .id(entity.getId())
                .email(entity.getEmail())
                .roles(entity.getRoles())
                .created(entity.getCreated())
                .modified(entity.getModified())
                .build();
    }

    /**
     * @param dto {@link UserDTO}
     * @return {@link UserEntity}
     */
    public static UserEntity toEntity(UserDTO dto) {
        return UserEntity.builder()
                .username(dto.getUsername())
                .name(dto.getName())
                .status(dto.getStatus())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .roles(dto.getRoles())
                .id(dto.getId())
                .created(dto.getCreated())
                .modified(dto.getModified())
                .build();
    }
}