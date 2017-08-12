package br.eti.archanjo.velociraptor.domain;

import br.eti.archanjo.velociraptor.utils.RedirectUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ShortDomain {
    /**
     * @param id       {@link String}
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     */
    public void doRedirect(String id,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        RedirectUtils.generateUrlId();
    }
}
