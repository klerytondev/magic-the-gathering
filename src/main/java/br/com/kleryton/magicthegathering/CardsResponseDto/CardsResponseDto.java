package br.com.kleryton.magicthegathering.CardsResponseDto;

import br.com.kleryton.magicthegathering.models.CardsModel;
import br.com.kleryton.magicthegathering.models.enums.FoilEnum;
import br.com.kleryton.magicthegathering.models.enums.LanguageEnum;

//Transferir atributos de resposta enviadas ao client
public class CardsResponseDto {

	private Long id;
	private String name;
	private String edition;
	private LanguageEnum language;
	private FoilEnum foil;
	private Double price;
	private Integer totalCards;

	public CardsResponseDto() {
	}

	public CardsResponseDto(CardsModel cardsModel) {
		this.id = cardsModel.getId();
		this.name = cardsModel.getName();
		this.edition = cardsModel.getEdition();
		this.language = cardsModel.getLanguage();
		this.foil = cardsModel.getFoil();
		this.price = cardsModel.getPrice();
//		this.totalCards = cardsModel.getTotalCards();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LanguageEnum getLanguage() {
		return language;
	}

	public void setLanguage(LanguageEnum language) {
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

	public Integer getTotalCards() {
		return totalCards;
	}

	public void setTotalCards(Integer totalCards) {
		this.totalCards = totalCards;
	}

}
