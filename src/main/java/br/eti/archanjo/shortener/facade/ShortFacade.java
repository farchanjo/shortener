package br.eti.archanjo.shortener.facade;

import br.eti.archanjo.shortener.domain.ShortDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ShortFacade {

    private final ShortDomain shortDomain;

    @Autowired
    public ShortFacade(ShortDomain shortDomain) {
        this.shortDomain = shortDomain;
    }

    /**
     * @param id       {@link String}
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     */
    public void doRedirect(String id,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        shortDomain.doRedirect(id, request, response);
    }
}
