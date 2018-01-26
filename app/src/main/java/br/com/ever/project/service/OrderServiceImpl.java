package br.com.ever.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ever.project.dto.Ingredient;
import br.com.ever.project.dto.Snack;
import br.com.ever.project.vo.Order;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private SnackService snackService;

	@Override
	public Order getOrder() {
		List<Ingredient> ingredientList = ingredientService.findAllIngredients();
		List<Snack> snackList = snackService.findAllSnacks();

		return Order.builder().ingredients(ingredientList).snacks(snackList).build();
	}
}
