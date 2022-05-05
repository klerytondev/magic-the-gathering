package br.com.kleryton.magicthegathering.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kleryton.magicthegathering.models.CardsList;
import br.com.kleryton.magicthegathering.requestDto.CardsRequestDto;
import br.com.kleryton.magicthegathering.services.CardsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/v1/magic-the-gathering")
@Api(value = "Magic The Gathering")
//Otimiza o acesso a API
@CrossOrigin(origins = "*")
public class CardsController {

	@Autowired
	CardsService cardsService;

	// SaveCard
	@ApiOperation(value = "Salva um novo card em uma lista de cards")
	@PostMapping("/card/add")
	public ResponseEntity<Object> saveCard(@RequestBody @Valid CardsRequestDto cardsRequestDto,
			@PathVariable Long idCardList) {
		CardsList cardsList = cardsService.createCard(cardsRequestDto, idCardList);
		return ResponseEntity.status(HttpStatus.CREATED).body(cardsList);
	}

}
