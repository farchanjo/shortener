package br.eti.archanjo.velociraptor.utils.parsers;

import br.eti.archanjo.velociraptor.dtos.DomainDTO;
import br.eti.archanjo.velociraptor.entities.mysql.DomainEntity;

public class DomainParser {

    /**
     * @param entity {@link DomainEntity}
     * @return {@link DomainDTO}
     */
    public static DomainDTO toDTO(DomainEntity entity) {
        return DomainDTO.builder()
                .status(entity.getStatus())
                .id(entity.getId())
                .token(entity.getToken())
                .domain(entity.getDomain())
                .created(entity.getCreated())
                .ssl(entity.isSsl())
                .modified(entity.getModified())
                .build();
    }

    /**
     * @param dto {@link DomainDTO}
     * @return {@link DomainEntity}
     */
    public static DomainEntity toEntity(DomainDTO dto) {
        return DomainEntity.builder()
                .status(dto.getStatus())
                .id(dto.getId())
                .token(dto.getToken())
                .domain(dto.getDomain())
                .ssl(dto.isSsl())
                .created(dto.getCreated())
                .modified(dto.getModified())
                .build();
    }
}