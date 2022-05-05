package br.com.kleryton.magicthegathering.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.kleryton.magicthegathering.models.enums.FoilEnum;
import br.com.kleryton.magicthegathering.models.enums.languageEnum;

@Entity
@Table(name = "TB_CARDS")
public class CardsModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name_card", nullable = true)
	private String name;

	@Column(name = "edition", nullable = false)
	private String edition;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private languageEnum language;

	@Column(nullable = false)
	private FoilEnum foil;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "total_cards")
	private Integer totalCards;

	public CardsModel() {
	}

	public CardsModel(String name, String edition, languageEnum language, FoilEnum foil, Double price,
			Integer totalCards) {
		super();
		this.name = name;
		this.edition = edition;
		this.language = language;
		this.foil = foil;
		this.price = price;
		this.totalCards = totalCards;

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

	public Integer getTotalCards() {
		return totalCards;
	}

	public void setTotalCards(Integer totalCards) {
		this.totalCards = totalCards;
	}

}
