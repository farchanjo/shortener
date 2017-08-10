package br.eti.archanjo.shortener.repositories.mysql;

import br.eti.archanjo.shortener.entities.mysql.DomainEntity;
import org.springframework.data.repository.CrudRepository;

public interface DomainRepository extends CrudRepository<DomainEntity, Long> {
}
