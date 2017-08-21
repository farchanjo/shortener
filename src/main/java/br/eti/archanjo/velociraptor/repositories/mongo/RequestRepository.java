package br.eti.archanjo.velociraptor.repositories.mongo;

import br.eti.archanjo.velociraptor.entities.mongo.RequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestRepository extends MongoRepository<RequestEntity, String> {
    long countAllByUrlId(Long id);

    Page<RequestEntity> findAllByOrderByCreatedDesc(Pageable page);
}
