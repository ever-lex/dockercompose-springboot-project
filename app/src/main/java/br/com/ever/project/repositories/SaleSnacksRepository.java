package br.com.ever.project.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.ever.project.model.SaleSnackEntity;

@Repository
public interface SaleSnacksRepository extends MongoRepository<SaleSnackEntity, String> {

	SaleSnackEntity findByName(String name);

}
