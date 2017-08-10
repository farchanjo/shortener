package br.eti.archanjo.shortener.facade;

import br.eti.archanjo.shortener.domain.Domain;
import br.eti.archanjo.shortener.dtos.DomainDTO;
import br.eti.archanjo.shortener.dtos.UserDTO;
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

    public DomainDTO create(UserDTO client) throws Exception {
        return domain.create(client);
    }
}
