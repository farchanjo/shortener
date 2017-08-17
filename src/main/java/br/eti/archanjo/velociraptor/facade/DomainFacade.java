package br.eti.archanjo.velociraptor.facade;

import br.eti.archanjo.velociraptor.domain.Domain;
import br.eti.archanjo.velociraptor.dtos.DomainDTO;
import br.eti.archanjo.velociraptor.dtos.UserDTO;
import br.eti.archanjo.velociraptor.enums.Roles;
import br.eti.archanjo.velociraptor.enums.Status;
import br.eti.archanjo.velociraptor.exceptions.NotAuthorizedException;
import br.eti.archanjo.velociraptor.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DomainFacade {

    private final Domain domain;

    @Autowired
    public DomainFacade(Domain domain) {
        this.domain = domain;
    }

    /**
     * @param domainDTO {@link DomainDTO}
     * @param client    {@link UserDTO}
     * @return {@link DomainDTO}
     * @throws Exception
     */
    public DomainDTO create(DomainDTO domainDTO, UserDTO client) throws Exception {
        if (!client.getRoles().equals(Roles.ADMIN))
            throw new NotAuthorizedException("You dont have access for this context. Only admins");
        return domain.create(domainDTO);
    }

    /**
     * @param page   {@link Integer}
     * @param limit  {@link Integer}
     * @param status {@link Status}
     * @param client {@link UserDTO}  @return {@link Page<DomainDTO>}
     */
    public Page<DomainDTO> listDomains(Integer page, Integer limit,
                                       Status status, UserDTO client) {
        return domain.listAll(page, limit, status);
    }

    /**
     * @param domains {@link String}
     * @param status {@link Status}
     * @param client {@link UserDTO}
     * @return {@link DomainDTO}
     */
    public DomainDTO findByDomain(String domains, Status status, UserDTO client) throws NotFoundException {
        return domain.findByDomain(domains, status);
    }
}
