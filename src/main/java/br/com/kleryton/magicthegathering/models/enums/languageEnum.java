package br.com.kleryton.magicthegathering.models.enums;

import javax.validation.constraints.Pattern;

public enum languageEnum {

	INGLÊS("ingles"), PORTUGUES("portugues"), JAPONES("japones");

	// @regex validar os possiveis tipos de indiomas
	@Pattern(regexp = "INGLES|PORTUGES|JAPONES")
	private String language;

	private languageEnum(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public void setIndioma(String language) {
		this.language = language;
	}

}
