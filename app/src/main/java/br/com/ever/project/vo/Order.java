package br.com.ever.project.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.ever.project.dto.Ingredient;
import br.com.ever.project.dto.Snack;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Ingredient> ingredients;
	
	private List<Snack> snacks;
	
	@NotNull
	@NotEmpty
	private String idSnack;
	
	private String idIngredient;
	
	private Integer extraAmount;

}
