package br.com.ever.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ever.project.dto.Ingredient;
import br.com.ever.project.dto.Snack;
import br.com.ever.project.service.IngredientService;
import br.com.ever.project.service.SnackService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/fast-food/api")
public class FastFoodRestApiController {

	@Autowired
	IngredientService ingredientService;

	@Autowired
	SnackService snackService;

	@RequestMapping(value = "/ingredient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody Ingredient ingredient) {
		log.info("Salvando ingrediente nome {}", ingredient.getName());

		try {
			ingredientService.saveIngredient(ingredient);
		} catch (Exception ex) {
			log.error("Ocorreu um erro ao salvar ingrediente {}", ingredient, ex);
				    return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);

	}

	@RequestMapping(value = "/snack", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody Snack snack) {
		log.info("Salvando ingrediente nome {}", snack.getName());

		snackService.saveSnack(snack);
		return new ResponseEntity<Snack>(snack, HttpStatus.OK);
	}
}