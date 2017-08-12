package br.eti.archanjo.shortener.api;

import br.eti.archanjo.shortener.constants.PathConstants;
import br.eti.archanjo.shortener.dtos.DomainDTO;
import br.eti.archanjo.shortener.facade.DomainFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public DomainDTO createDomain(@RequestBody DomainDTO domainDTO) throws Exception {
        return domainFacade.create(domainDTO, getClient());
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<DomainDTO> listDomain(@RequestParam("page") Integer page,
                                      @RequestParam("limit") Integer limit) throws Exception {
        return domainFacade.listDomains(page, limit, getClient());
    }
}
