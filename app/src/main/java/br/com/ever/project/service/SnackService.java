package br.com.ever.project.service;


import java.util.List;

import br.com.ever.project.dto.Snack;
import br.com.ever.project.model.SnackEntity;

public interface SnackService {

	Snack findById(String id);

	SnackEntity findByName(String name);

	void saveSnack(Snack snack);

	void updateSnack(Snack snack);

	void deleteSnackById(String id);

	void deleteAllSnacks();

	List<Snack> findAllSnacks();

}