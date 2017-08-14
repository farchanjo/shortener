package br.eti.archanjo.velociraptor.domain;

import br.eti.archanjo.velociraptor.configs.PropertiesConfig;
import br.eti.archanjo.velociraptor.entities.mysql.UrlEntity;
import br.eti.archanjo.velociraptor.enums.Status;
import br.eti.archanjo.velociraptor.repositories.mysql.UrlRepository;
import br.eti.archanjo.velociraptor.services.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ShortDomain {
    private static Logger logger = LoggerFactory.getLogger(ShortDomain.class);
    private final RequestService requestService;
    private final UrlRepository urlRepository;
    private final PropertiesConfig config;

    @Autowired
    public ShortDomain(RequestService requestService,
                       UrlRepository urlRepository,
                       PropertiesConfig config) {
        this.requestService = requestService;
        this.urlRepository = urlRepository;
        this.config = config;
    }

    /**
     * @param id       {@link String}
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     */
    public void doRedirect(String id,
                           HttpServletRequest request,
                           HttpServletResponse response) {


        UrlEntity entity = urlRepository.findByShortValueAndStatus(id, Status.ENABLED);
        try {
            if (entity != null) {
                /*
                 * Sending to persist the request.
                 */
                requestService.process(request, id);

                response.sendRedirect(entity.getDestination());
            } else {
                logger.debug(String.format("Sendind to default domain (%s) because its null", config.getDefaultRedirect()));
                response.sendRedirect(config.getDefaultRedirect().toString());
            }
        } catch (IOException e) {
            logger.warn("ShortDomain{doRedirect}", e);
        }
    }
}
