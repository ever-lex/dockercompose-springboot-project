package br.com.ever.project.factory;

import br.com.ever.project.dto.ExtraIngredient;
import br.com.ever.project.dto.Snack;
import br.com.ever.project.enumerator.IngredientsTypeEnum;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class PromotionSnackMeat implements PromotionSnack{

	@SuppressWarnings("unused")
	public Double calculator (Snack snack, ExtraIngredient extra) {

		log.info("Calculando valor do lanche muita carne");

		Double price = 0d;

		Double addition = 1.5;

		Double overlap = 1d;

		Double meatPrice = 0d;

		for (int index = 0; index < snack.getIngredientsWrapper().getIngredients().size(); index++) {
			price += snack.getIngredientsWrapper().getIngredients().get(index).getPrice();

			if(IngredientsTypeEnum.HAMBURGUER_CARNE.getTypeCod().equals(snack.getIngredientsWrapper().getIngredients().get(index).getType())){
				meatPrice += snack.getIngredientsWrapper().getIngredients().get(index).getPrice();
			}
		}

		if ((extra.getExtra() % 3) == 0) {
			overlap = (extra.getExtra() / addition);
			return (price + (meatPrice * overlap));
		}

		return (price + (meatPrice * overlap));
	}

}
