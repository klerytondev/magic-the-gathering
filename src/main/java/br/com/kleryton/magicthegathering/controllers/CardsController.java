package br.com.kleryton.magicthegathering.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kleryton.magicthegathering.models.CardsModel;
import br.com.kleryton.magicthegathering.models.PlayerModel;
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
	@ApiOperation(value = "Salva um novo card")
	@PostMapping("/card/idCardList/idPlayer")
	public ResponseEntity<Object> saveCard(@RequestBody @Valid CardsRequestDto cardsRequestDto,
			@PathVariable Long idCardList, @PathVariable Long idPlayer) {
		PlayerModel playerModel = cardsService.createCard(cardsRequestDto, idCardList, idPlayer);
		return ResponseEntity.status(HttpStatus.CREATED).body(playerModel);
	}

	// Read All By Order
	@ApiOperation(value = "Retorna todos os cards salvos em uma lista de cards pela ordem")
	@GetMapping("/card/order/{idCardList}")
	public ResponseEntity<List<CardsModel>> getAllCardsInOrder(@PathVariable Long idCardList) {
		List<CardsModel> cardModels = cardsService.getAllCardsToCardsById(idCardList);
		return ResponseEntity.status(HttpStatus.OK).body(cardModels);
	}

	// Read All By Price
	@ApiOperation(value = "Retorna todos os cards salvos em uma lista de cards pelo valor")
	@GetMapping("/card/price/{idCardList}")
	public ResponseEntity<List<CardsModel>> getAllCardsInPrice(@PathVariable Long idCardList) {
		List<CardsModel> cardModels = cardsService.getAllCardsToCardsPrice(idCardList);
		return ResponseEntity.status(HttpStatus.OK).body(cardModels);
	}

	// Delete One By id
	@ApiOperation(value = "Deleta um card de uma lista de acordo com o id(carta) passado")
	@DeleteMapping("/cards/delete/idPlayer")
	public ResponseEntity<Object> DeletecardModel(@RequestParam("id") Long id, @PathVariable Long idPlayer) {
		Boolean cardDelete = cardsService.deleteCard(id, idPlayer);
		if (cardDelete == true) {
			return ResponseEntity.status(HttpStatus.OK).body("Card deleted successfully");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Could not delete card");
	}

	// Update By id
	@ApiOperation(value = "Atualiza um cartão de uma conta de acordo com o id(cartão) passado")
	@PutMapping("/cards/update/idPlayer")
	public ResponseEntity<CardsModel> updateCardsModel(@RequestParam("id") Long id,
			@RequestBody @Valid CardsRequestDto cardsRequestDto, @PathVariable Long idPlayer) {
		CardsModel cardsModel = cardsService.cardUpdate(id, cardsRequestDto, idPlayer);
		return ResponseEntity.status(HttpStatus.OK).body(cardsModel);
	}

}
