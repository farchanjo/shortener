package br.eti.archanjo.velociraptor.domain;

import br.eti.archanjo.velociraptor.dtos.DomainDTO;
import br.eti.archanjo.velociraptor.entities.mysql.DomainEntity;
import br.eti.archanjo.velociraptor.enums.Status;
import br.eti.archanjo.velociraptor.exceptions.AlreadyExistsException;
import br.eti.archanjo.velociraptor.exceptions.BadRequestException;
import br.eti.archanjo.velociraptor.exceptions.NotFoundException;
import br.eti.archanjo.velociraptor.repositories.mysql.DomainRepository;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger logger = LoggerFactory.getLogger(Domain.class);

    private final DomainRepository domainRepository;
    private final DozerBeanMapper mapper;

    @Autowired
    public Domain(DomainRepository domainRepository, DozerBeanMapper mapper) {
        this.domainRepository = domainRepository;
        this.mapper = mapper;
    }

    /**
     * @param domain {@link DomainDTO}
     * @return {@link DomainDTO}
     */
    public DomainDTO create(DomainDTO domain) throws NotFoundException, BadRequestException, AlreadyExistsException {
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
            if (domainRepository.countAllByDomainContains(domain.getDomain()) > 0)
                throw new AlreadyExistsException("This domains already exist.");
            entity = DomainEntity.builder().build();
            entity.setDomain(domain.getDomain());
            entity.setStatus(domain.getStatus());
            entity.setToken(UUID.randomUUID().toString());
        }
        entity = domainRepository.save(entity);
        return mapper.map(entity, DomainDTO.class);
    }

    /**
     * @param page   {@link Integer}
     * @param limit  {@link Integer }
     * @param status {@link Status}
     * @return {@link Page<DomainDTO>}
     */
    public Page<DomainDTO> listAll(Integer page, Integer limit, Status status) {
        Page<DomainEntity> entities = domainRepository.findAllByStatus(new PageRequest(page, limit), status);
        return entities.map(source -> mapper.map(source, DomainDTO.class));
    }

    /**
     * @param domains {@link String}
     * @param status  {@link Status}
     * @return {@link DomainDTO}
     * @throws NotFoundException
     */
    public DomainDTO findByDomain(String domains, Status status) throws NotFoundException {
        DomainEntity entity = domainRepository.findByDomainAndStatus(domains, status);
        if (entity == null)
            throw new NotFoundException("This domain does not exist or disabled");
        return mapper.map(entity, DomainDTO.class);
    }
}
