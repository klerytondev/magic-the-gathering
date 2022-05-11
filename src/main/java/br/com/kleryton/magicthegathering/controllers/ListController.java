package br.com.kleryton.magicthegathering.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kleryton.magicthegathering.models.CardList;
import br.com.kleryton.magicthegathering.repositories.CardsListRepository;
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
	@ApiOperation(value = "Salva uma nova lista")
	@PostMapping("/card/CardList")
	public ResponseEntity<String> saveList() {
		CardList cardList = new CardList();
		cardsListRepository.save(cardList);
		return ResponseEntity.status(HttpStatus.CREATED).body("Lista de Cards criada com sucesso!");
	}

}
