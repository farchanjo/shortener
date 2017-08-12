package br.eti.archanjo.velociraptor.api;

import br.eti.archanjo.velociraptor.constants.PathConstants;
import br.eti.archanjo.velociraptor.facade.ShortFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = PathConstants.ROOT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RootResource extends GenericResource {
    private static Logger logger = LoggerFactory.getLogger(RootResource.class);

    private final ShortFacade shortFacade;

    @Autowired
    public RootResource(ShortFacade shortFacade) {
        this.shortFacade = shortFacade;
    }

    @RequestMapping(path = "{id}")
    public void redirect(@PathVariable("id") String id,
                         HttpServletResponse response,
                         HttpServletRequest request) throws Exception {
        shortFacade.doRedirect(id, request, response);
    }
}
