package br.com.ever.project.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.ever.project.WebApplication;
import br.com.ever.project.dto.ExtraIngredient;
import br.com.ever.project.dto.Ingredient;
import br.com.ever.project.dto.IngredientWrapper;
import br.com.ever.project.dto.Snack;
import br.com.ever.project.enumerator.IngredientsTypeEnum;
import br.com.ever.project.enumerator.SnacksTypeEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { WebApplication.class })
@WebAppConfiguration
public class SaleSnackServiceTest {

	@Autowired
	SaleSnackService saleSnackService;

	@Test
	public void mustReturnValueForSnackDefault() {

		IngredientWrapper ingredientWrapper = createIngredientWrapper();
		
		Snack snack = Snack.builder().name(SnacksTypeEnum.X_BURGER.getName()).ingredientsWrapper(ingredientWrapper)
				.build();
		ExtraIngredient extra = null;

		Double price = saleSnackService.calculateSnackPrice(snack, extra);

		// Validação
		Assert.assertEquals("Retorna o valor do lanche sem adicional", 4.5, price, 0.01);
	}

	@Test
	public void mustReturnValueForSnackMeat() {

		IngredientWrapper ingredientWrapper = createIngredientWrapper();

		Snack snack = Snack.builder().name(SnacksTypeEnum.X_BURGER.getName()).ingredientsWrapper(ingredientWrapper)
				.build();

		Ingredient burguer = new Ingredient("burguer", IngredientsTypeEnum.HAMBURGUER_CARNE.getType(), 3.0, 3);
		ExtraIngredient extra = new ExtraIngredient(burguer, 3);

		Double price = saleSnackService.calculateSnackPrice(snack, extra);

		// Validação
		Assert.assertEquals("Retorna o valor do a promoção de carne mais 3 paga 2", 10.5, price, 0.01);
	}

	@Test
	public void mustReturnValueForSnackCheese() {

		IngredientWrapper ingredientWrapper = createIngredientWrapper();

		Snack snack = Snack.builder().name(SnacksTypeEnum.X_BURGER.getName()).ingredientsWrapper(ingredientWrapper)
				.build();
		
		Ingredient queijo = new Ingredient("queijo", IngredientsTypeEnum.QUEIJO.getType(), 1.5, 5);
		ExtraIngredient extra = new ExtraIngredient(queijo, 3);

		Double price = saleSnackService.calculateSnackPrice(snack, extra);

		// Validação
		Assert.assertEquals("Retorna o valor do a promoção de queijo de mais 3 paga 2", 7.5, price, 0.01);
	}

	@Test
	public void mustReturnValueForSnackLigth() {

		IngredientWrapper ingredientWrapper = new IngredientWrapper();
		Ingredient burguer = new Ingredient("burguer", IngredientsTypeEnum.HAMBURGUER_CARNE.getType(), 3.0, 3);
		Ingredient queijo = new Ingredient("queijo", IngredientsTypeEnum.QUEIJO.getType(), 1.5, 5);
		Ingredient alface = new Ingredient("alface", IngredientsTypeEnum.ALFACE.getType(), 0.4, 1);

		List<Ingredient> ingredients = new ArrayList<>();

		ingredients.add(burguer);
		ingredients.add(queijo);
		ingredients.add(alface);

		ingredientWrapper.setIngredients(ingredients);

		Snack snack = Snack.builder().name(SnacksTypeEnum.X_BURGER.getName()).ingredientsWrapper(ingredientWrapper)
				.build();

		ExtraIngredient extra = new ExtraIngredient(alface, 1);

		Double price = saleSnackService.calculateSnackPrice(snack, extra);

		// Validação
		Assert.assertEquals("Retorna o valor do a promoção light", 5.39, price, 0.01);
	}

	@Test
	public void mustReturnValueForSnackBaconWithLettuce() {

		IngredientWrapper ingredientWrapper = new IngredientWrapper();
		Ingredient burguer = new Ingredient("burguer", IngredientsTypeEnum.HAMBURGUER_CARNE.getType(), 3.0, 3);
		Ingredient queijo = new Ingredient("queijo", IngredientsTypeEnum.QUEIJO.getType(), 1.5, 5);
		Ingredient bacon = new Ingredient("bacon", IngredientsTypeEnum.BACON.getType(), 2.0, 2);
		Ingredient alface = new Ingredient("alface", IngredientsTypeEnum.ALFACE.getType(), 0.4, 1);

		List<Ingredient> ingredients = new ArrayList<>();

		ingredients.add(burguer);
		ingredients.add(queijo);
		ingredients.add(alface);
		ingredients.add(bacon);

		ingredientWrapper.setIngredients(ingredients);

		Snack snack = Snack.builder().name(SnacksTypeEnum.X_BACON.getName()).ingredientsWrapper(ingredientWrapper)
				.build();

		ExtraIngredient extra = new ExtraIngredient(alface, 1);

		Double price = saleSnackService.calculateSnackPrice(snack, extra);

		// Validação
		Assert.assertEquals("Retorna o valor do lanche total", 6.9, price, 0.01);
	}

	IngredientWrapper createIngredientWrapper() {
		IngredientWrapper ingredientWrapper = new IngredientWrapper();
		
		Ingredient burguer = new Ingredient("burguer", IngredientsTypeEnum.HAMBURGUER_CARNE.getType(), 3.0, 3);
		Ingredient queijo = new Ingredient("queijo", IngredientsTypeEnum.QUEIJO.getType(), 1.5, 5);
		
		List<Ingredient> ingredients = new ArrayList<>();

		ingredients.add(burguer);
		ingredients.add(queijo);

		ingredientWrapper.setIngredients(ingredients);

		return ingredientWrapper;
	}
}
