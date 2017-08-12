package br.eti.archanjo.velociraptor.configs.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication(scanBasePackages = "br.eti.archanjo")
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400,
        redisFlushMode = RedisFlushMode.IMMEDIATE,
        redisNamespace = "velociraptor")
@EnableJpaRepositories("br.eti.archanjo.velociraptor.repositories.mysql")
@EntityScan("br.eti.archanjo.velociraptor.entities.mysql")
@EnableMongoRepositories(basePackages = "br.eti.archanjo.velociraptor.repositories.mongo")
public class ShortenerApplication implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(ShortenerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ShortenerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("SYSTEM Loaded.");
    }
}
