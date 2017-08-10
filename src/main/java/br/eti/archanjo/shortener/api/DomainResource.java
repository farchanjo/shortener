package br.eti.archanjo.shortener.api;

import br.eti.archanjo.shortener.constants.PathConstants;
import br.eti.archanjo.shortener.dtos.DomainDTO;
import br.eti.archanjo.shortener.facade.DomainFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = PathConstants.DOMAINS, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DomainResource extends GenericResource {
    private static Logger logger = LoggerFactory.getLogger(DomainResource.class);

    private final DomainFacade domainFacade;

    @Autowired
    public DomainResource(DomainFacade domainFacade) {
        this.domainFacade = domainFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public DomainDTO createDomain() throws Exception {
        return domainFacade.create(getClient());
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<DomainDTO> listDomain() throws Exception {
        return null;
    }
}
