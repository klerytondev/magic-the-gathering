package br.com.kleryton.magicthegathering.requestDto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

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

	@Column(name = "foil", nullable = false)
	private Boolean foil;

	@PositiveOrZero(message = "{campo.positiveorzero.postivo}")
	private Double price;

}
