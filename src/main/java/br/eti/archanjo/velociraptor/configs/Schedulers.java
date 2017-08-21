package br.eti.archanjo.velociraptor.configs;

import br.eti.archanjo.velociraptor.repositories.mongo.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class Schedulers {
    private static Logger logger = LoggerFactory.getLogger(Schedulers.class);

    private final RequestRepository requestRepository;

    @Autowired
    public Schedulers(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Scheduled(fixedDelay = 5000L, initialDelay = 10000L)
    private void consolidateData() {
        logger.info("Started");
/*        Page<RequestEntity> entities = requestRepository.findAllByOrderByCreatedDesc(new PageRequest(0, 1000));
        int pageCounter = 0;
        while (pageCounter < entities.getTotalPages()) {
            entities.forEach(p -> {

            });
            *//*
             * Getting new page
             *//*
            entities = requestRepository.findAllByOrderByCreatedDesc(new PageRequest(0, 1000));
            pageCounter++;
        }*/
        logger.info("finished");
    }
}
