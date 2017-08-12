package br.eti.archanjo.velociraptor.domain;

import br.eti.archanjo.velociraptor.dtos.UrlDTO;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Url {
    public UrlDTO generate(UrlDTO urlDTO) {
        return null;
    }
}
