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

}
