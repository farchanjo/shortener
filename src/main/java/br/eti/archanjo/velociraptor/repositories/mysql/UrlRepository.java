package br.eti.archanjo.velociraptor.repositories.mysql;

import br.eti.archanjo.velociraptor.entities.mysql.UrlEntity;
import br.eti.archanjo.velociraptor.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<UrlEntity, Long> {
    Page<UrlEntity> findAllByDomainIdAndStatus(Pageable page, Long id, Status status);
}
