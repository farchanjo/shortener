package br.eti.archanjo.shortener.facade;

import br.eti.archanjo.shortener.domain.Domain;
import br.eti.archanjo.shortener.dtos.DomainDTO;
import br.eti.archanjo.shortener.dtos.UserDTO;
import br.eti.archanjo.shortener.enums.Roles;
import br.eti.archanjo.shortener.exceptions.NotAuthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
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
}
