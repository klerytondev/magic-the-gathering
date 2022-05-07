package br.com.kleryton.magicthegathering.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kleryton.magicthegathering.models.CardsList;
import br.com.kleryton.magicthegathering.models.CardsModel;
import br.com.kleryton.magicthegathering.models.PlayerModel;
import br.com.kleryton.magicthegathering.repositories.CardsListRepository;
import br.com.kleryton.magicthegathering.repositories.CardsRepository;
import br.com.kleryton.magicthegathering.repositories.PlayerRepository;
import br.com.kleryton.magicthegathering.requestDto.CardsRequestDto;
import br.com.kleryton.magicthegathering.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class CardsService {

	@Autowired
	CardsRepository cardsRepository;

	@Autowired
	CardsListRepository cardsListRepository;

	@Autowired
	PlayerRepository playerRepository;

	// Create list cards
	@Transactional
	public PlayerModel createCard(CardsRequestDto cardsRequestDto, Long id, Long idPlayer) {

		// Verifica se a lista de cards existe no banco
		Optional<CardsList> cardListOptional = cardsListRepository.findById(id);
		cardListOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("List cards not found."));

		// Verifica se o player existe no banco
		Optional<PlayerModel> playerModelOptional = playerRepository.findById(idPlayer);
		playerModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Player not found."));

		// Converte o cardsRequestDto em um CardsModel
		CardsModel cardModelPersist = new CardsModel();
		cardModelPersist = convertDtoToModel(cardsRequestDto);

		// Seta um card na lista de cards
		cardListOptional.get().setCards(cardModelPersist);

		// Seta uma Lista de cards e um player
		playerModelOptional.get().setCardsList(cardListOptional.get());

		PlayerModel playerModelPersist = playerRepository.save(playerModelOptional.get());

		return playerModelPersist;
	}

	// Read All Order
	@Transactional
	public List<CardsModel> getAllCardsToCardsById(Long id) {
		CardsList cardsList;
		// Verifica se existe conta no banco de dados com o id passado
		try {
			cardsList = getAccountModelById(id);
		} catch (Exception e) {
			throw new ObjetoNaoEncontradoException("List cards not found by id.");
		}
		// Seta card de cards passada em uma lista de cards
		List<CardsModel> cards = cardsList.getCards();
		// Verifica se a lista de cards está vazia
		if (cards.isEmpty())
			throw new ObjetoNaoEncontradoException("Card not found by CardList.");

		List<CardsModel> listCards = new ArrayList<>(cards);
		Collections.sort(listCards, Comparator.comparing(CardsModel::getName));
		return listCards;
	}

	// Read All Price
	@Transactional
	public List<CardsModel> getAllCardsToCardsPrice(Long id) {
		CardsList cardsList;
		// Verifica se existe conta no banco de dados com o id passado
		try {
			cardsList = getAccountModelById(id);
		} catch (Exception e) {
			throw new ObjetoNaoEncontradoException("List cards not found by id.");
		}
		// Seta card de cards passada em uma lista de cards
		List<CardsModel> cards = cardsList.getCards();
		// Verifica se a lista de cards está vazia
		if (cards.isEmpty())
			throw new ObjetoNaoEncontradoException("Card not found by CardList.");

		List<CardsModel> listCards = new ArrayList<>(cards);
		Collections.sort(listCards, Comparator.comparing(CardsModel::getPrice));
		return listCards;
	}

	// Delete by id
	@Transactional
	public String delete(Long id) {

		Optional<CardsModel> cardsModelOptional = cardsRepository.findById(id);

		// Verifica se card existe
		if (!cardsModelOptional.isPresent()) {
			throw new ObjetoNaoEncontradoException("Card not found.");
		}

		cardsRepository.deleteById(id);
		return "Card deleted successfully.";
	}

	// Update by id
	@Transactional
	public CardsModel cardUpdate(Long id, CardsRequestDto cardsRequestDto, Long idPlayer) {

		// Busca no banco de dados se existe card com o id passado
		Optional<CardsModel> cardModelOptional = cardsRepository.findById(id);
		cardModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Card not found."));

		// Verifica se o player existe no banco
		Optional<PlayerModel> playerModelOptional = playerRepository.findById(idPlayer);
		playerModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Player not found."));

		// Atualiza o campo price da card

		cardModelOptional.get().setPrice(cardsRequestDto.getPrice());

		return cardModelOptional.get();

	}

	@Transactional
	protected CardsList getAccountModelById(Long id) {

		// Verifica se existe uma account no banco de dados
		Optional<CardsList> cardListOptional = cardsListRepository.findById(id);
		cardListOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("List cards not found."));
		return cardListOptional.get();
	}

	// Coverte uma card em uma response DTO
//	public CardsResponseDto convertModelToDTO(CardsModel cardsModel) {
//
//		CardsResponseDto cardsResponseDto = new CardsResponseDto(cardsModel);
//		accountModel.setCard(accountModel.getCard());
//
//		return accountResponseDto;
//	}

	// Coverte um request DTO em Card
	public CardsModel convertDtoToModel(CardsRequestDto cardsRequestDto) {

		CardsModel cardsModel = new CardsModel();
		cardsModel.setName(cardsRequestDto.getName());
		cardsModel.setEdition(cardsRequestDto.getEdition());
		cardsModel.setLanguage(cardsRequestDto.getLanguage());
		cardsModel.setFoil(cardsRequestDto.getFoil());
		cardsModel.setPrice(cardsRequestDto.getPrice());
		cardsModel.setTotalCards(cardsRequestDto.getTotalCards());

		return cardsModel;
	}
}
