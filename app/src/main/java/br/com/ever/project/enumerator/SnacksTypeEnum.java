package br.com.ever.project.enumerator;

public enum SnacksTypeEnum {

	X_BACON("X-Bacon"), X_BURGER("X-Burger"), X_EGG("X-Egg"), X_EGG_BACON("X-Egg Bacon");

	private String name;

	private SnacksTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}