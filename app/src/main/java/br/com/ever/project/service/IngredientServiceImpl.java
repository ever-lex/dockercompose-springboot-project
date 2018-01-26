package br.com.ever.project.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ever.project.dto.Ingredient;
import br.com.ever.project.model.IngredientEntity;
import br.com.ever.project.repositories.IngredientRepository;

@Service("ingredientService")
@Transactional
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;

	@Override
	public Ingredient findById(String id) {

		IngredientEntity ingredientEntity = ingredientRepository.findOne(id);

		ModelMapper mapper = new ModelMapper();
		Ingredient dto = mapper.map(ingredientEntity, Ingredient.class);

		return dto;
	}

	@Override
	public IngredientEntity findByName(String name) {
		return ingredientRepository.findByName(name);
	}

	@Override
	public void saveIngredient(Ingredient ingredient) {

		IngredientEntity ingredientEntity = IngredientEntity.builder().name(ingredient.getName())
				.price(ingredient.getPrice()).type(ingredient.getType()).build();
		ingredientRepository.save(ingredientEntity);
	}

	@Override
	public void updateIngredient(Ingredient ingredient) {
		saveIngredient(ingredient);
	}

	@Override
	public void deleteIngredientById(String id) {
		ingredientRepository.delete(id);
	}

	@Override
	public void deleteAllIngredients() {
		ingredientRepository.deleteAll();
	}

	@Override
	public List<Ingredient> findAllIngredients() {
		List<Ingredient> ingredients = new ArrayList<>();

		List<IngredientEntity> ingredientList = ingredientRepository.findAll();
		for (IngredientEntity ingredientEntity : ingredientList) {
			ModelMapper mapper = new ModelMapper();
			Ingredient dto = mapper.map(ingredientEntity, Ingredient.class);

			ingredients.add(dto);
		}
		return ingredients;
	}
}
