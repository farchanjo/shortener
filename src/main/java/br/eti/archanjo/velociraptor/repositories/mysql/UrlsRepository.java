package br.eti.archanjo.velociraptor.repositories.mysql;

import br.eti.archanjo.velociraptor.entities.mysql.UrlEntity;
import org.springframework.data.repository.CrudRepository;

public interface UrlsRepository extends CrudRepository<UrlEntity, Long> {
}
