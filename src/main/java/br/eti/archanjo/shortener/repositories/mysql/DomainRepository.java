package br.eti.archanjo.shortener.repositories.mysql;

import br.eti.archanjo.shortener.entities.mysql.DomainEntity;
import br.eti.archanjo.shortener.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DomainRepository extends CrudRepository<DomainEntity, Long> {
    Page<DomainEntity> findAllByStatus(Pageable page, Status status);
}
