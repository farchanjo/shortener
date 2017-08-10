package br.eti.archanjo.shortener.repositories.mysql;

import br.eti.archanjo.shortener.entities.mysql.UrlsEntity;
import org.springframework.data.repository.CrudRepository;

public interface UrlsRepository extends CrudRepository<UrlsEntity, Long> {
}
