package br.eti.archanjo.shortener.configs;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "shortener")
public class PropertiesConfig {
    private HttpLimits httplimits;

    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    @Getter
    @Setter
    public static class HttpLimits {
        private int limit;
        private int page;
    }
}