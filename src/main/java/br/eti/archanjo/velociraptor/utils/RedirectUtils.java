package br.eti.archanjo.velociraptor.utils;

import br.eti.archanjo.velociraptor.pojo.Request;
import org.apache.commons.lang.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public class RedirectUtils {
    public static String generateUrlId() {
        return RandomStringUtils.randomAlphanumeric(6);
    }

    public static Request parseRequest(HttpServletRequest req) {
        req.getScheme();
        return Request.builder()
                .userAgent(req.getHeader("User-Agent"))
                .uri(URI.create(String.format("%s://%s", req.getScheme(), req.getServerName())))
                .ip(req.getHeader("X-Forwarded-For") == null ? req.getRemoteAddr() : req.getHeader("X-Forwarded-For"))
                .build();
    }
}
