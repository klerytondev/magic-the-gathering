package br.com.kleryton.magicthegathering.models.enums;

import javax.validation.constraints.Pattern;

public enum FoilEnum {

	SIM("sim"), NAO("nao");

	// @regex validar os possiveis tipos de foil
	@Pattern(regexp = "SIM|NAO")
	private String foil;

	private FoilEnum(String foil) {
		this.foil = foil;
	}

	public String getFoil() {
		return foil;
	}

	public void setFoil(String foil) {
		this.foil = foil;
	}

}
