package br.eti.archanjo.velociraptor.utils.parsers;

import br.eti.archanjo.velociraptor.dtos.UrlDTO;
import br.eti.archanjo.velociraptor.entities.mysql.UrlEntity;

public class UrlParser {

    /**
     * @param entity {@link UrlEntity}
     * @return {@link UrlDTO}
     */
    public static UrlDTO toDTO(UrlEntity entity) {
        UrlDTO dto = UrlDTO.builder()
                .status(entity.getStatus())
                .id(entity.getId())
                .destination(entity.getDestination())
                .expirationDate(entity.getExpirationDate())
                .maxRequests(entity.getMaxRequests())
                .shortValue(entity.getShortValue())
                .created(entity.getCreated())
                .domainId(entity.getDomain() != null ? entity.getDomain().getId() : null)
                .modified(entity.getModified())
                .build();
        if (entity.getDomain() != null) {
            if (entity.getDomain().isSsl()) {
                dto.setCompleteUrl(String.format("https://%s/%s", entity.getDomain().getDomain(), entity.getShortValue()));
            } else {
                dto.setCompleteUrl(String.format("http://%s/%s", entity.getDomain().getDomain(), entity.getShortValue()));
            }
        }
        return dto;
    }

    /**
     * @param dto {@link UrlDTO}
     * @return {@link UrlEntity}
     */
    public static UrlEntity toEntity(UrlDTO dto) {
        return UrlEntity.builder()
                .status(dto.getStatus())
                .id(dto.getId())
                .shortValue(dto.getShortValue())
                .maxRequests(dto.getMaxRequests())
                .expirationDate(dto.getExpirationDate())
                .destination(dto.getDestination())
                .created(dto.getCreated())
                .modified(dto.getModified())
                .build();
    }
}