package br.eti.archanjo.velociraptor.services;

import br.eti.archanjo.velociraptor.entities.mongo.RequestEntity;
import br.eti.archanjo.velociraptor.entities.mysql.UrlEntity;
import br.eti.archanjo.velociraptor.enums.Status;
import br.eti.archanjo.velociraptor.pojo.Request;
import br.eti.archanjo.velociraptor.repositories.mongo.RequestRepository;
import br.eti.archanjo.velociraptor.repositories.mysql.UrlRepository;
import br.eti.archanjo.velociraptor.utils.RandomUtils;
import br.eti.archanjo.velociraptor.utils.RedirectUtils;
import com.newrelic.api.agent.Trace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    /**
     * @param request {@link HttpServletRequest}
     * @param id      {@link String}
     */
    @Async
    @Trace(metricName = "RequestService{process}", async = true, dispatcher = true)
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
                urlRepository.save(entity);
                logger.debug(String.format("%s disabled", id));
            }
            long total = requestRepository.countAllByUrlId(entity.getId());
            if (entity.getMaxRequests() != null &&
                    entity.getMaxRequests() > total) {
                entity.setStatus(Status.DISABLED);
                urlRepository.save(entity);
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

    /**
     * Generate data
     */
    public void generateData() {
        List<RequestEntity> data = new ArrayList<>();
        Instant now = Instant.now();
        for (int i = 0; i < TimeUnit.DAYS.toSeconds(2); i++) {
            for (int a = 0; a < 100; a++) {
                data.add(RandomUtils.getRequest(Date.from(now.plus(i, ChronoUnit.SECONDS))));
            }
            requestRepository.save(data);
            data.clear();
        }
    }
}
