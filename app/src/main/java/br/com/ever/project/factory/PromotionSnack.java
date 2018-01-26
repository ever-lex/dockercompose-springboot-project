package br.com.ever.project.factory;

import br.com.ever.project.dto.ExtraIngredient;
import br.com.ever.project.dto.Snack;

public interface PromotionSnack {

	public Double calculator (Snack snack, ExtraIngredient extra);

}
