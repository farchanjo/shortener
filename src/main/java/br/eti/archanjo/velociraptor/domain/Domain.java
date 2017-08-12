package br.eti.archanjo.velociraptor.domain;

import br.eti.archanjo.velociraptor.dtos.DomainDTO;
import br.eti.archanjo.velociraptor.dtos.UserDTO;
import br.eti.archanjo.velociraptor.entities.mysql.DomainEntity;
import br.eti.archanjo.velociraptor.entities.mysql.UserEntity;
import br.eti.archanjo.velociraptor.enums.Status;
import br.eti.archanjo.velociraptor.exceptions.BadRequestException;
import br.eti.archanjo.velociraptor.exceptions.NotFoundException;
import br.eti.archanjo.velociraptor.repositories.mysql.DomainRepository;
import br.eti.archanjo.velociraptor.repositories.mysql.UserRepository;
import br.eti.archanjo.velociraptor.utils.parsers.DomainParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Domain {

    private final DomainRepository domainRepository;

    private final UserRepository userRepository;

    @Autowired
    public Domain(DomainRepository domainRepository, UserRepository userRepository) {
        this.domainRepository = domainRepository;
        this.userRepository = userRepository;
    }

    /**
     * @param domain {@link DomainDTO}
     * @param client {@link UserDTO}
     * @return {@link DomainDTO}
     */
    public DomainDTO create(DomainDTO domain, UserDTO client) throws NotFoundException, BadRequestException {
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
            UserEntity user = userRepository.findOne(client.getId());
            entity = DomainEntity.builder().build();
            entity.setDomain(domain.getDomain());
            entity.setStatus(domain.getStatus());
            entity.setToken(UUID.randomUUID().toString());
            entity.setUsers(Collections.singletonList(user));
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
