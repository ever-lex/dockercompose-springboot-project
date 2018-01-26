package br.com.ever.project.factory;

import br.com.ever.project.dto.ExtraIngredient;
import br.com.ever.project.dto.Snack;
import br.com.ever.project.enumerator.IngredientsTypeEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PromotionSnackCheese implements PromotionSnack {

	@SuppressWarnings("unused")
	public Double calculator(Snack snack, ExtraIngredient extra) {

		log.info("Calculando valor do lanche muito queijo");

		Double price = 0d;

		Double addition = 1.5;

		Double overlap = 1d;

		Double cheesePrice = 0d;

		for (int index = 0; index < snack.getIngredientsWrapper().getIngredients().size(); index++) {
			price += snack.getIngredientsWrapper().getIngredients().get(index).getPrice();

			if(IngredientsTypeEnum.QUEIJO.getTypeCod().equals(snack.getIngredientsWrapper().getIngredients().get(index).getType())){
				cheesePrice += snack.getIngredientsWrapper().getIngredients().get(index).getPrice();
			}
		}

		if ((extra.getExtra() % 3) == 0) {
			overlap = (extra.getExtra() / addition);
			return (price + (cheesePrice * overlap));
		}

		return (price + (cheesePrice * overlap));
	}

}
