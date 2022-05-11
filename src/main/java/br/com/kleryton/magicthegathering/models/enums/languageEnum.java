package br.com.kleryton.magicthegathering.models.enums;

import javax.validation.constraints.Pattern;

public enum LanguageEnum {

	INGLES("ingles"), PORTUGUES("portugues"), JAPONES("japones");

	// @regex validar os possiveis tipos de indiomas
	@Pattern(regexp = "INGLES|PORTUGES|JAPONES")
	private String language;

	private LanguageEnum(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
