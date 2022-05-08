package br.com.kleryton.magicthegathering.requestDto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.kleryton.magicthegathering.models.PlayerModel;

public class PlayerRequestDto {

	@NotEmpty(message = "{campo.namecard.obrigatorio}")
	@Length(max = 50, message = "{campo.namecard.caracteres}")
	private String name;

	public PlayerRequestDto() {
	}

	public PlayerRequestDto(PlayerModel playerModel) {
		this.name = playerModel.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
