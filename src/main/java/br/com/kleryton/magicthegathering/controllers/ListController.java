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

import br.com.kleryton.magicthegathering.models.PlayerModel;
import br.com.kleryton.magicthegathering.repositories.CardsListRepository;
import br.com.kleryton.magicthegathering.requestDto.CardsRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/v1/magic-the-gathering")
@Api(value = "Magic The Gathering")
//Otimiza o acesso a API
@CrossOrigin(origins = "*")
public class ListController {
	
	@Autowired
	CardsListRepository cardsListRepository;
	
	// SaveList
		@ApiOperation(value = "Salva uma novas lista")
		@PostMapping("/card/idCardList/idPlayer")
		public ResponseEntity<Object> saveCard(@RequestBody @Valid CardsRequestDto cardsRequestDto,
				@PathVariable Long idCardList, @PathVariable Long idPlayer) {
			PlayerModel playerModel = cardsService.createCard(cardsRequestDto, idCardList, idPlayer);
			return ResponseEntity.status(HttpStatus.CREATED).body(playerModel);
		}

}
