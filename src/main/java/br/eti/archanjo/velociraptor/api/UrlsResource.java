package br.eti.archanjo.velociraptor.api;

import br.eti.archanjo.velociraptor.constants.PathConstants;
import br.eti.archanjo.velociraptor.dtos.UrlDTO;
import br.eti.archanjo.velociraptor.enums.Status;
import br.eti.archanjo.velociraptor.facade.UrlsFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = PathConstants.API + PathConstants.URLS, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UrlsResource extends GenericResource {
    private static Logger logger = LoggerFactory.getLogger(UrlsResource.class);

    private final UrlsFacade urlsFacade;

    @Autowired
    public UrlsResource(UrlsFacade urlsFacade) {
        this.urlsFacade = urlsFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public UrlDTO urlsFacade(@RequestBody UrlDTO urlDTO) throws Exception {
        return urlsFacade.generate(urlDTO);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<UrlDTO> listUrls(@RequestParam("page") Integer page,
                                 @RequestParam("limit") Integer limit,
                                 @RequestParam("domainId") Long domainId,
                                 @RequestParam(value = "status", required = false,
                                         defaultValue = "ENABLED") Status status) throws Exception {
        return urlsFacade.listAll(page, limit, status, domainId);
    }
}
