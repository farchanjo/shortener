package br.eti.archanjo.velociraptor.domain;

import br.eti.archanjo.velociraptor.dtos.UrlDTO;
import br.eti.archanjo.velociraptor.entities.mysql.DomainEntity;
import br.eti.archanjo.velociraptor.entities.mysql.UrlEntity;
import br.eti.archanjo.velociraptor.exceptions.NotFoundException;
import br.eti.archanjo.velociraptor.repositories.mysql.DomainRepository;
import br.eti.archanjo.velociraptor.repositories.mysql.UrlRepository;
import br.eti.archanjo.velociraptor.utils.RedirectUtils;
import br.eti.archanjo.velociraptor.utils.parsers.UrlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Url {
    private final DomainRepository domainRepository;

    private final UrlRepository urlRepository;

    @Autowired
    public Url(DomainRepository domainRepository, UrlRepository urlRepository) {
        this.domainRepository = domainRepository;
        this.urlRepository = urlRepository;
    }

    public UrlDTO generate(UrlDTO urlDTO) throws NotFoundException {
        DomainEntity domain = domainRepository.findOne(urlDTO.getDomainId());
        if (domain == null)
            throw new NotFoundException(String.format("This domainId %s does not exist", urlDTO.getDomainId()));
        UrlEntity url = UrlEntity.builder()
                .shortValue(RedirectUtils.generateUrlId())
                .expirationDate(urlDTO.getExpirationDate())
                .destination(urlDTO.getDestination())
                .maxRequests(urlDTO.getMaxRequests())
                .status(urlDTO.getStatus())
                .build();
        url = urlRepository.save(url);
        domain.getUrls().add(url);
        domainRepository.save(domain);
        UrlDTO dto = UrlParser.toDTO(url);
        dto.setDomainId(domain.getId());
        if (domain.isSSL()) {
            dto.setCompleteUrl(String.format("https://%s/%s", domain.getDomain(), url.getShortValue()));
        } else {
            dto.setCompleteUrl(String.format("http://%s/%s", domain.getDomain(), url.getShortValue()));
        }
        return dto;
    }
}
