package br.com.ever.project.factory;

import br.com.ever.project.dto.ExtraIngredient;
import br.com.ever.project.dto.Snack;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class PromotionSnackDefault implements PromotionSnack{

	public Double calculator (Snack snack, ExtraIngredient extra) {

		log.info("Calculando valor do lanche normal");

		Double price = 0d;

		for (int index = 0; index < snack.getIngredientsWrapper().getIngredients().size(); index++) {
			price += snack.getIngredientsWrapper().getIngredients().get(index).getPrice();
		}
		return price;
	}
}
