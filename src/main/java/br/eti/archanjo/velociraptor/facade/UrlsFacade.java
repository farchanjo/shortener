package br.eti.archanjo.velociraptor.facade;

import br.eti.archanjo.velociraptor.domain.Url;
import br.eti.archanjo.velociraptor.dtos.UrlDTO;
import br.eti.archanjo.velociraptor.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URI;

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
     * @return {@link UrlDTO}
     */
    public UrlDTO generate(UrlDTO urlDTO) throws Exception {
        if (urlDTO.getStatus() == null)
            throw new BadRequestException("Missing status");

        if (urlDTO.getDomainId() == null)
            throw new BadRequestException("Missing domainId");

        if (urlDTO.getDestination() == null)
            throw new BadRequestException("Missing destination");

        URI uri = URI.create(urlDTO.getDestination());
        if (uri.getScheme() == null)
            throw new BadRequestException("URL is wrong");

        return url.generate(urlDTO);
    }
}
