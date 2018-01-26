package br.com.ever.project.enumerator;

import org.apache.commons.lang3.StringUtils;

public enum IngredientsTypeEnum {

	ALFACE(1, "Alface"), BACON(2, "Bacon"), HAMBURGUER_CARNE(3, "Hambúrguer de carne"), OVO(4, "Ovo"), QUEIJO(5,
			"Queijo");

	private Integer typeCod;

	private String type;

	private IngredientsTypeEnum(Integer typeCod, String type) {
		this.typeCod = typeCod;
		this.type = type;
	}

	public Integer getTypeCod() {
		return typeCod;
	}

	public String getType() {
		return type;
	}

	public static String getType(Integer typeCod) {

		String typeResult = StringUtils.EMPTY;

		if (null != typeCod) {
			for (IngredientsTypeEnum typeIngredient : IngredientsTypeEnum.values()) {

				if (typeCod.equals(typeIngredient.getTypeCod())) {
					typeResult = typeIngredient.getType();
				}
			}
		}
		return typeResult;
	}
}