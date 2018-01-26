package br.com.ever.project.model;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "ingredient")
public class IngredientEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@NonNull
	private String name;

	@NonNull
	private Double price;

	@NonNull
	private Integer type;


}
