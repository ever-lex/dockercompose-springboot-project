package br.com.ever.project.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.ever.project.model.IngredientEntity;

@Repository
public interface IngredientRepository extends MongoRepository<IngredientEntity, String> {

	IngredientEntity findByName(String name);

}
