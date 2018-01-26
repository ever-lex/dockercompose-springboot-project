package br.com.ever.project.service;


import java.util.List;

import br.com.ever.project.dto.Ingredient;
import br.com.ever.project.model.IngredientEntity;

public interface IngredientService {

	Ingredient findById(String id);

	IngredientEntity findByName(String name);

	void saveIngredient(Ingredient ingredient);

	void updateIngredient(Ingredient ingredient);

	void deleteIngredientById(String id);

	void deleteAllIngredients();

	 List<Ingredient> findAllIngredients();

}