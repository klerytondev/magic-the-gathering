package br.com.kleryton.magicthegathering.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kleryton.magicthegathering.models.CardList;
import br.com.kleryton.magicthegathering.models.PlayerModel;
import br.com.kleryton.magicthegathering.repositories.CardsListRepository;
import br.com.kleryton.magicthegathering.repositories.PlayerRepository;
import br.com.kleryton.magicthegathering.services.exceptions.ObjetoNaoEncontradoException;
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
	
	@Autowired
	PlayerRepository playerRepository;
	

	// SaveList
	@ApiOperation(value = "Salva uma nova lista")
	@PostMapping("/cardList/{idPlayer}")
	public ResponseEntity<String> saveList(@PathVariable (value = "idPlayer") Long idPlayer) {
		
		// Verifica se o player existe no banco
		Optional<PlayerModel> playerModelOptional = playerRepository.findById(idPlayer);
		playerModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Player not found."));
		CardList cardList = new CardList();
		cardsListRepository.save(cardList);
		playerModelOptional.get().setCardsList(cardList);
		playerRepository.save(playerModelOptional.get());
		return ResponseEntity.status(HttpStatus.CREATED).body("Lista de Cards criada com sucesso!: " + playerModelOptional.get());
	}

}
