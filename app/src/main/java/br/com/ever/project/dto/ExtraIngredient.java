package br.com.ever.project.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraIngredient implements Serializable{

	private static final long serialVersionUID = 1L;

	private Ingredient ingredient;

	private Integer extra;
}
