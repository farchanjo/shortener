package br.eti.archanjo.shortener.api;

import br.eti.archanjo.shortener.constants.PathConstants;
import br.eti.archanjo.shortener.facade.ShortFacade;
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
@RequestMapping(path = PathConstants.SHORT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ShortResource extends GenericResource {
    private static Logger logger = LoggerFactory.getLogger(ShortResource.class);

    private final ShortFacade shortFacade;

    @Autowired
    public ShortResource(ShortFacade shortFacade) {
        this.shortFacade = shortFacade;
    }

    @RequestMapping(path = "{id}")
    public void redirect(@PathVariable("id") String id,
                         HttpServletResponse response,
                         HttpServletRequest request) throws Exception {
        shortFacade.doRedirect(id, request, response);
    }
}
