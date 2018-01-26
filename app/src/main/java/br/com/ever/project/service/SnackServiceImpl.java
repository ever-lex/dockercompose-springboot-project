package br.com.ever.project.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ever.project.dto.Ingredient;
import br.com.ever.project.dto.IngredientWrapper;
import br.com.ever.project.dto.Snack;
import br.com.ever.project.model.IngredientEntity;
import br.com.ever.project.model.SnackEntity;
import br.com.ever.project.repositories.SnacksRepository;

@Service("snackService")
@Transactional
public class SnackServiceImpl implements SnackService {

	@Autowired
	private SnacksRepository snackRepository;

	@Override
	public Snack findById(String id) {
		SnackEntity snackEntity = snackRepository.findOne(id);
		
		ModelMapper mapperSnake = new ModelMapper();
		IngredientWrapper ingredientsWrapper = new IngredientWrapper();
		
		List<Ingredient> ingredients = new ArrayList<>();
		
		for (IngredientEntity ingredientEntity : snackEntity.getIngredients()) {
			ModelMapper ingredientMapper = new ModelMapper();
			Ingredient dto = ingredientMapper.map(ingredientEntity, Ingredient.class);
			
			ingredients.add(dto);
		}
		ingredientsWrapper.setIngredients(ingredients);
		Snack dto = mapperSnake.map(snackEntity, Snack.class);
		dto.setIngredientsWrapper(ingredientsWrapper);
		
		return dto;
	}

	@Override
	public SnackEntity findByName(String name) {
		return snackRepository.findByName(name);
	}

	@Override
	public void saveSnack(Snack snack) {

		List<IngredientEntity> ingredients = new ArrayList<>();
		IngredientEntity ingredientEntity = null;

		for (int index = 0; index < snack.getIngredientsWrapper().getIngredients().size(); index++) {
			ingredientEntity = IngredientEntity.builder()
					.name(snack.getIngredientsWrapper().getIngredients().get(index).getName())
					.price(snack.getIngredientsWrapper().getIngredients().get(index).getPrice())
					.type(snack.getIngredientsWrapper().getIngredients().get(index).getType()).build();
			ingredients.add(ingredientEntity);
		}

		SnackEntity entity = SnackEntity.builder().name(snack.getName()).ingredients(ingredients).build();
		snackRepository.save(entity);
	}

	@Override
	public void updateSnack(Snack snack) {
		saveSnack(snack);
	}

	@Override
	public void deleteSnackById(String id) {
		snackRepository.delete(id);
	}

	@Override
	public void deleteAllSnacks() {
		snackRepository.deleteAll();
	}

	@Override
	public List<Snack> findAllSnacks() {
		List<Snack> snackList = new ArrayList<>();

		List<SnackEntity> snackEntitylist = snackRepository.findAll();
		
		for (SnackEntity snackEntity : snackEntitylist) {
			
			ModelMapper mapperSnake = new ModelMapper();
			IngredientWrapper ingredientsWrapper = new IngredientWrapper();
			
			List<Ingredient> ingredients = new ArrayList<>();
			
			for (IngredientEntity ingredientEntity : snackEntity.getIngredients()) {
				ModelMapper mapper = new ModelMapper();
				Ingredient dto = mapper.map(ingredientEntity, Ingredient.class);
				
				ingredients.add(dto);
			}
			ingredientsWrapper.setIngredients(ingredients);
			Snack dto = mapperSnake.map(snackEntity, Snack.class);
			dto.setIngredientsWrapper(ingredientsWrapper);
			snackList.add(dto);
		}
		return snackList;
	}
}
