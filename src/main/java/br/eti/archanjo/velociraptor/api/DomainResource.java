package br.eti.archanjo.velociraptor.api;

import br.eti.archanjo.velociraptor.constants.PathConstants;
import br.eti.archanjo.velociraptor.dtos.DomainDTO;
import br.eti.archanjo.velociraptor.enums.Status;
import br.eti.archanjo.velociraptor.facade.DomainFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = PathConstants.API + PathConstants.DOMAINS, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
                                      @RequestParam("limit") Integer limit,
                                      @RequestParam(value = "status", required = false,
                                              defaultValue = "ENABLED") Status status) throws Exception {
        return domainFacade.listDomains(page, limit, status, getClient());
    }

    @RequestMapping(path = PathConstants.FIND)
    public DomainDTO listByDomain(@RequestParam("domain") String domain,
                                  @RequestParam(value = "status", required = false, defaultValue = "ENABLED") Status status)
            throws Exception {
        return domainFacade.findByDomain(domain, status,getClient());
    }
}
