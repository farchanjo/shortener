package br.eti.archanjo.velociraptor.facade;

import br.eti.archanjo.velociraptor.domain.Url;
import br.eti.archanjo.velociraptor.dtos.UrlDTO;
import br.eti.archanjo.velociraptor.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UrlsFacade {

    private final Url url;

    @Autowired
    public UrlsFacade(Url url) {
        this.url = url;
    }

    /**
     * @param urlDTO {@link UrlDTO}
     * @param client {@link UserDTO}
     * @return {@link UrlDTO}
     */
    public UrlDTO generate(UrlDTO urlDTO, UserDTO client) {
        return url.generate(urlDTO);
    }
}
