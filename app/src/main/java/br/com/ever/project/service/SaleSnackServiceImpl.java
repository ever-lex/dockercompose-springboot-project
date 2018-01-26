package br.com.ever.project.service;

import java.util.List;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ever.project.dto.ExtraIngredient;
import br.com.ever.project.dto.Ingredient;
import br.com.ever.project.dto.Snack;
import br.com.ever.project.enumerator.IngredientsTypeEnum;
import br.com.ever.project.factory.PriceSnackFactory;
import br.com.ever.project.model.SaleSnackEntity;
import br.com.ever.project.repositories.SaleSnacksRepository;
import br.com.ever.project.vo.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("saleSnackService")
@Transactional
public class SaleSnackServiceImpl implements SaleSnackService {

	@Autowired
	private SaleSnacksRepository saleSnackRepository;
	
	@Autowired
	private SnackService snackService;
	
	@Autowired
	private IngredientService ingredientService;

	@Override
	public SaleSnackEntity findById(String id) {
		return saleSnackRepository.findOne(id);
	}

	@Override
	public SaleSnackEntity findByName(String name) {
		return saleSnackRepository.findByName(name);
	}

	@Override
	public void saveSaleSnack(Snack snack, ExtraIngredient extra, Double price) {
		log.info("Salvando pedido {} com preço {} ",snack.getName(), price);
		saleSnackRepository.save(SaleSnackEntity.builder().name(snack.getName()).price(price).build());
	}

	public Double calculateSnackPrice(Snack snack, ExtraIngredient extra) {
		Supplier<PriceSnackFactory> priceFactory = PriceSnackFactory::new;

		if (null != extra && IngredientsTypeEnum.ALFACE.getTypeCod().equals(extra.getIngredient().getType())
				&& null != snack) {
			
			if (!this.isContainBacon(snack)) {
				return priceFactory.get().getPromotion("LIGHT").calculator(snack, extra);
			}
		} else if (null != extra && IngredientsTypeEnum.HAMBURGUER_CARNE.getTypeCod().equals(extra.getIngredient().getType())
				&& null != snack) {
			
			return priceFactory.get().getPromotion("MEAT").calculator(snack, extra);
		} else if (null != extra && IngredientsTypeEnum.QUEIJO.getTypeCod().equals(extra.getIngredient().getType())
				&& null != snack) {
			
			return priceFactory.get().getPromotion("CHEESE").calculator(snack, extra);
		} 
			return priceFactory.get().getPromotion("DEFAULT").calculator(snack, extra);
	}

	private Boolean isContainBacon(Snack snack) {

		for (int index = 0; index < snack.getIngredientsWrapper().getIngredients().size(); index++) {
			if (IngredientsTypeEnum.BACON.getTypeCod()
					.equals(snack.getIngredientsWrapper().getIngredients().get(index).getType()))
				return true;
		}
		return false;
	}

	@Override
	public void updateSaleSnack(Snack snack, ExtraIngredient extra, Double price) {
		saveSaleSnack(snack, extra, price);
	}

	@Override
	public void deleteSaleSnackById(String id) {
		saleSnackRepository.delete(id);
	}

	@Override
	public void deleteAllSaleSnacks() {
		saleSnackRepository.deleteAll();
	}

	@Override
	public List<SaleSnackEntity> findAllSaleSnacks() {
		return saleSnackRepository.findAll();
	}

	@Override
	public void saveOrder(Order order) {
		
		Snack snack = snackService.findById(order.getIdSnack());
		ExtraIngredient extra = null;
		Ingredient ingredient = null;
		if(null != order && StringUtils.isNotBlank(order.getIdIngredient())){	
			ingredient = ingredientService.findById(order.getIdIngredient());
			extra = new ExtraIngredient(ingredient, order.getExtraAmount() !=  null ? order.getExtraAmount() : 0);
		}
		Double price = calculateSnackPrice(snack, extra);
		this.saveSaleSnack(snack, extra, price);
	}
}
