package br.eti.archanjo.shortener.repositories.mysql;

import br.eti.archanjo.shortener.entities.mysql.DomainEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DomainRepository extends CrudRepository<DomainEntity, Long> {
    Page<DomainEntity> findAll(Pageable page);
}
