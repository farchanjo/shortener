package br.eti.archanjo.velociraptor.repositories.mysql;

import br.eti.archanjo.velociraptor.entities.mysql.DomainEntity;
import br.eti.archanjo.velociraptor.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DomainRepository extends CrudRepository<DomainEntity, Long> {
    Page<DomainEntity> findAllByStatus(Pageable page, Status status);

    DomainEntity findByDomainAndStatus(String domain, Status status);

    Long countAllByDomainContains(String domain);
}
