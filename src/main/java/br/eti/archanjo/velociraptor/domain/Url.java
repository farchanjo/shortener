package br.eti.archanjo.velociraptor.domain;

import br.eti.archanjo.velociraptor.dtos.UrlDTO;
import br.eti.archanjo.velociraptor.entities.mysql.DomainEntity;
import br.eti.archanjo.velociraptor.entities.mysql.UrlEntity;
import br.eti.archanjo.velociraptor.enums.Status;
import br.eti.archanjo.velociraptor.exceptions.NotFoundException;
import br.eti.archanjo.velociraptor.repositories.mysql.DomainRepository;
import br.eti.archanjo.velociraptor.repositories.mysql.UrlRepository;
import br.eti.archanjo.velociraptor.utils.RedirectUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Url {
    private final DomainRepository domainRepository;

    private final UrlRepository urlRepository;

    private final DozerBeanMapper mapper;

    @Autowired
    public Url(DomainRepository domainRepository, UrlRepository urlRepository, DozerBeanMapper mapper) {
        this.domainRepository = domainRepository;
        this.urlRepository = urlRepository;
        this.mapper = mapper;
    }

    public UrlDTO generate(UrlDTO urlDTO) throws NotFoundException {
        DomainEntity domain = domainRepository.findOne(urlDTO.getDomainId());
        if (domain == null)
            throw new NotFoundException(String.format("This domainId %s does not exist", urlDTO.getDomainId()));
        UrlEntity url = UrlEntity.builder()
                .shortValue(RedirectUtils.generateUrlId())
                .expirationDate(urlDTO.getExpirationDate())
                .destination(urlDTO.getDestination())
                .maxRequests(urlDTO.getMaxRequests())
                .status(urlDTO.getStatus())
                .domain(domain)
                .build();
        url = urlRepository.save(url);
        UrlDTO dto = mapper.map(url, UrlDTO.class);
        if (dto != null) {
            completeDTO(dto, url);
        }
        return dto;
    }

    /**
     * @param page     {@link Integer}
     * @param limit    {@link Integer}
     * @param status   {@link Status}
     * @param domainId {@link Long}
     * @return {@link Page<UrlDTO>}
     */
    public Page<UrlDTO> listAll(Integer page, Integer limit, Status status, Long domainId) {
        Page<UrlEntity> entities = urlRepository.findAllByDomainIdAndStatus(new PageRequest(page, limit), domainId, status);
        return entities.map(source -> {
            UrlDTO dto = mapper.map(source, UrlDTO.class);
            if (dto != null) {
                completeDTO(dto, source);
            }
            return dto;
        });
    }

    /**
     * @param dto    {@link UrlDTO}
     * @param source {@link UrlEntity}
     */
    private void completeDTO(UrlDTO dto, UrlEntity source) {
        dto.setDomainId(source.getDomain() != null ? source.getDomain().getId() : null);
        if (source.getDomain() != null) {
            if (source.getDomain().isSsl()) {
                dto.setCompleteUrl(String.format("https://%s/%s", source.getDomain().getDomain(), source.getShortValue()));
            } else {
                dto.setCompleteUrl(String.format("http://%s/%s", source.getDomain().getDomain(), source.getShortValue()));
            }
        }
    }
}
