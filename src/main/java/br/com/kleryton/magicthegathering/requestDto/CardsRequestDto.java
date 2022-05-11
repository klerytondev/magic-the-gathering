package br.com.kleryton.magicthegathering.requestDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import br.com.kleryton.magicthegathering.models.CardsModel;
import br.com.kleryton.magicthegathering.models.enums.FoilEnum;
import br.com.kleryton.magicthegathering.models.enums.languageEnum;

//Transferir e manipular atributos recebidos por parametro de Cards
public class CardsRequestDto {

	@NotEmpty(message = "{campo.namecard.obrigatorio}")
	@Length(max = 50, message = "{campo.namecard.caracteres}")
	private String name;

	@NotEmpty(message = "{campo.edition.obrigatorio}")
	@Length(max = 30, message = "{campo.edition.caracteres}")
	private String edition;

	private languageEnum language;

	@Pattern(regexp = "SIM|NAO", message = "campo.foil.valores}")
	private FoilEnum foil;

	@PositiveOrZero(message = "{campo.pricePositiveorzero.postivo}")
	private Double price;

	public CardsRequestDto() {
	}

	public CardsRequestDto(CardsModel cardsModel) {
		this.name = cardsModel.getName();
		this.edition = cardsModel.getEdition();
		this.language = cardsModel.getLanguage();
		this.foil = cardsModel.getFoil();
		this.price = cardsModel.getPrice();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public languageEnum getLanguage() {
		return language;
	}

	public void setLanguage(languageEnum language) {
		this.language = language;
	}

	public FoilEnum getFoil() {
		return foil;
	}

	public void setFoil(FoilEnum foil) {
		this.foil = foil;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
