package br.com.kleryton.magicthegathering.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_CARDSlIST")
public class CardList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cards_id", foreignKey = @ForeignKey(name = "fk_cards"))
	private List<CardsModel> cards;

	public CardList() {
	}

	public CardList(List<CardsModel> cards) {
		super();
		this.cards = cards;
	}
	
	public Long getId() {
		return id;
	}

	public List<CardsModel> getCards() {
		return cards;
	}

	public void setCards(CardsModel cards) {
		this.cards.add(cards);
	}
}
