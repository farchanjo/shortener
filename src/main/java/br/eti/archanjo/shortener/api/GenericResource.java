package br.eti.archanjo.shortener.api;

import br.eti.archanjo.shortener.dtos.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

abstract class GenericResource {
    private static Logger logger = LoggerFactory.getLogger(GenericResource.class);

    UserDTO getClient() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal == null) {
            return null;
        }

        try {
            return (UserDTO) principal;
        } catch (Exception e) {
            logger.warn("GenericResource{getClient}", e);
            return null;
        }
    }
}