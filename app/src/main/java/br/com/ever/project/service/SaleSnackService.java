package br.com.ever.project.service;


import java.util.List;

import br.com.ever.project.dto.ExtraIngredient;
import br.com.ever.project.dto.Snack;
import br.com.ever.project.model.SaleSnackEntity;
import br.com.ever.project.vo.Order;

public interface SaleSnackService {

	SaleSnackEntity findById(String id);

	SaleSnackEntity findByName(String name);

	void saveSaleSnack(Snack snack, ExtraIngredient extra, Double price);
	
	void saveOrder(Order order);

	void updateSaleSnack(Snack snack, ExtraIngredient extra, Double price);

	void deleteSaleSnackById(String id);

	void deleteAllSaleSnacks();

	List<SaleSnackEntity> findAllSaleSnacks();
	
	Double calculateSnackPrice(Snack snack, ExtraIngredient extra);

}