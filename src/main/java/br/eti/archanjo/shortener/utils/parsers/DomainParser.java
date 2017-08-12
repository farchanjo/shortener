package br.eti.archanjo.shortener.utils.parsers;

import br.eti.archanjo.shortener.dtos.DomainDTO;
import br.eti.archanjo.shortener.entities.mysql.DomainEntity;

public class DomainParser {

    /**
     * @param entity {@link DomainEntity}
     * @return {@link DomainDTO}
     */
    public static DomainDTO toDTO(DomainEntity entity) {
        return DomainDTO.builder()
                .status(entity.getStatus())
                .id(entity.getId())
                .domain(entity.getDomain())
                .created(entity.getCreated())
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
                .domain(dto.getDomain())
                .created(dto.getCreated())
                .modified(dto.getModified())
                .build();
    }
}