package br.eti.archanjo.velociraptor.api;

import br.eti.archanjo.velociraptor.constants.PathConstants;
import br.eti.archanjo.velociraptor.dtos.UrlDTO;
import br.eti.archanjo.velociraptor.facade.UrlsFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = PathConstants.URLS, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UrlsResource extends GenericResource {
    private static Logger logger = LoggerFactory.getLogger(UrlsResource.class);

    private final UrlsFacade urlsFacade;

    @Autowired
    public UrlsResource(UrlsFacade urlsFacade) {
        this.urlsFacade = urlsFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public UrlDTO urlsFacade(@RequestBody UrlDTO urlDTO) throws Exception {
        return urlsFacade.generate(urlDTO, getClient());
    }
}
