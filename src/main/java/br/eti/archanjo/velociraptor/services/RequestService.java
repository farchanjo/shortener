package br.eti.archanjo.velociraptor.services;

import br.eti.archanjo.velociraptor.entities.mongo.RequestEntity;
import br.eti.archanjo.velociraptor.entities.mysql.UrlEntity;
import br.eti.archanjo.velociraptor.enums.Status;
import br.eti.archanjo.velociraptor.pojo.Request;
import br.eti.archanjo.velociraptor.repositories.mongo.RequestRepository;
import br.eti.archanjo.velociraptor.repositories.mysql.UrlRepository;
import br.eti.archanjo.velociraptor.utils.RedirectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RequestService {
    private static Logger logger = LoggerFactory.getLogger(RequestService.class);

    private final UrlRepository urlRepository;

    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(UrlRepository urlRepository,
                          RequestRepository requestRepository) {
        this.urlRepository = urlRepository;
        this.requestRepository = requestRepository;
    }

    @Async
    public void process(HttpServletRequest request, String id) {
        Request req = RedirectUtils.parseRequest(request);
        checkForDisable(id);
        save(req, id);
    }

    /**
     * @param id {@link String}
     */
    private void checkForDisable(String id) {
        UrlEntity entity = urlRepository.findByShortValue(id);
        if (entity != null) {
            if (entity.getExpirationDate() != null &&
                    Instant.now().isAfter(entity.getExpirationDate().toInstant())) {
                entity.setStatus(Status.DISABLED);
                logger.debug(String.format("%s disabled", id));
            }
        }
    }

    /**
     * @param request {@link Request}
     * @param id      {@link String}
     */
    private void save(Request request, String id) {
        UrlEntity entity = urlRepository.findByShortValue(id);
        if (entity != null) {
            RequestEntity requestEntity = RequestEntity.builder()
                    .urlId(entity.getId())
                    .ip(request.getIp())
                    .referrer(request.getReferrer())
                    .userAgent(request.getUserAgent())
                    .domainId(entity.getDomain().getId())
                    .domain(entity.getDomain().getDomain())
                    .created(new Date())
                    .build();
            requestRepository.save(requestEntity);
        }
    }
}