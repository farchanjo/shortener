package br.eti.archanjo.shortener.domain;

import br.eti.archanjo.shortener.dtos.DomainDTO;
import br.eti.archanjo.shortener.entities.mysql.DomainEntity;
import br.eti.archanjo.shortener.enums.Status;
import br.eti.archanjo.shortener.exceptions.BadRequestException;
import br.eti.archanjo.shortener.exceptions.NotFoundException;
import br.eti.archanjo.shortener.repositories.mysql.DomainRepository;
import br.eti.archanjo.shortener.utils.parsers.DomainParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Domain {

    private final DomainRepository domainRepository;

    @Autowired
    public Domain(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    /**
     * @param domain {@link DomainDTO}
     * @return {@link DomainDTO}
     */
    public DomainDTO create(DomainDTO domain) throws NotFoundException, BadRequestException {
        if (domain.getStatus() == null || domain.getDomain() == null)
            throw new BadRequestException("Missing domain or status propertie.");

        DomainEntity entity;
        if (domain.getId() != null) {
            entity = domainRepository.findOne(domain.getId());
            if (entity == null)
                throw new NotFoundException("This domain does not exist.");
            entity.setId(domain.getId());
            entity.setDomain(domain.getDomain());
            entity.setStatus(domain.getStatus());
        } else {
            entity = DomainEntity.builder().build();
            entity.setDomain(domain.getDomain());
            entity.setStatus(domain.getStatus());
            entity.setToken(UUID.randomUUID().toString());
        }
        entity = domainRepository.save(entity);
        return DomainParser.toDTO(entity);
    }

    /**
     * @param page   {@link Integer}
     * @param limit  {@link Integer }
     * @param status {@link Status}
     * @return {@link Page<DomainDTO>}
     */
    public Page<DomainDTO> listAll(Integer page, Integer limit, Status status) {
        Page<DomainEntity> entities = domainRepository.findAllByStatus(new PageRequest(page, limit), status);
        return entities.map(DomainParser::toDTO);
    }
}
