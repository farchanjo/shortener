package br.eti.archanjo.shortener.repositories.mysql;

import br.eti.archanjo.shortener.entities.mysql.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
