package br.eti.archanjo.shortener.domain;

import br.eti.archanjo.shortener.dtos.DomainDTO;
import br.eti.archanjo.shortener.entities.mysql.DomainEntity;
import br.eti.archanjo.shortener.exceptions.BadRequestException;
import br.eti.archanjo.shortener.exceptions.NotFoundException;
import br.eti.archanjo.shortener.repositories.mysql.DomainRepository;
import br.eti.archanjo.shortener.utils.parsers.DomainParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
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
}
