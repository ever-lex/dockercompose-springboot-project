package br.com.ever.project.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.ever.project.model.SnackEntity;

@Repository
public interface SnacksRepository extends MongoRepository<SnackEntity, String> {

	SnackEntity findByName(String name);

}
