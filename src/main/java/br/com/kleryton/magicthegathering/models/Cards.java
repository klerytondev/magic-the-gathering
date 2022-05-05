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

import br.com.kleryton.magicthegathering.models.enums.languageEnum;

//informa que esta classe é uma entidade
@Entity
@Table(name = "TB_CARDS")
public class Cards implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name-card", nullable = false)
	private String name;

	@Column(name = "edition", nullable = false)
	private String edition;

	@Enumerated(EnumType.STRING)
	@Column(name = "language", nullable = false)
	private languageEnum language;

	@Column(name = "foil", nullable = false)
	private Double foil;

	@Column(name = "price", nullable = false)
	private Double price;

	private Double totalCards;

	public Cards() {
	}

	public Cards(String name, String edition, languageEnum language, Double foil, Double price, Double totalCards) {
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

	public Double getFoil() {
		return foil;
	}

	public void setFoil(Double foil) {
		this.foil = foil;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotalCards() {
		return totalCards;
	}

	public void setTotalCards(Double totalCards) {
		this.totalCards = totalCards;
	}

}
