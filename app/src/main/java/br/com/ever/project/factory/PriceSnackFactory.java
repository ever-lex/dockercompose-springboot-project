package br.com.ever.project.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PriceSnackFactory {

	final static Map<String, Supplier<PromotionSnack>> map = new HashMap<>();

	static {
		map.put("DEFAULT", PromotionSnackDefault::new);
		map.put("LIGHT", PromotionSnackLight::new);
		map.put("MEAT", PromotionSnackMeat::new);
		map.put("CHEESE", PromotionSnackCheese::new);
	}

	public PromotionSnack getPromotion(String type) {
		log.info("Criando classe do tipo {} ", type);

		Supplier<PromotionSnack> promotionSnack = map.get(type.toUpperCase());
		if (promotionSnack != null) {
			return promotionSnack.get();
		}
		throw new IllegalArgumentException("No such shape " + type.toUpperCase());
	}
}
