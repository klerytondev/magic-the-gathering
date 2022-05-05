package br.com.kleryton.magicthegathering.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.kleryton.magicthegathering.models.CardsModel;
import br.com.kleryton.magicthegathering.repositories.CardsRepository;
import br.com.kleryton.magicthegathering.requestDto.CardsRequestDto;
import br.com.kleryton.magicthegathering.services.exceptions.ConflictDeDadosException;
import br.com.kleryton.magicthegathering.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class CardsService {

	@Autowired
	CardsRepository cardsRepository;

	@Transactional
	// Create card
	public CardsModel create(CardsRequestDto cardsRequestDto) {

		CardsModel cardsModel = convertDtoToModel(cardsRequestDto);

		// Verifica se accountCode ou RegisteId já está em uso no banco
		try {
			cardsRepository.save(cardsModel);
		} catch (DataIntegrityViolationException e) {
			throw new ConflictDeDadosException("Card is already in use!");
		}
		return cardsModel;
	}

	// Read All
	@Transactional
	public List<CardsModel> findAll() {

		// Verifica se existe cards no banco, caso contrario retorna exception
		if (cardsRepository.findAll().isEmpty()) {
			throw new ObjetoNaoEncontradoException("Cards not found!");
		}
		// Salva Cards existentes no banco de dados em uma lista de cards
		List<CardsModel> cardsModelsList = new ArrayList<>();
		for (CardsModel cardsModel : cardsRepository.findAll()) {
			cardsModelsList.add(cardsModel);
		}
		return cardsModelsList;
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
	public Optional<CardsModel> updateAcoount(Long id, CardsRequestDto cardsRequestDto) {

		// Busca no banco de dados se existe account com o id passado
		Optional<CardsModel> cardModelOptional = cardsRepository.findById(id);
		cardModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Card not found."));

		// Atualiza o campo price da card

		cardModelOptional.get().setPrice(cardsRequestDto.getPrice());

//		CardsResponseDto cardsResponseDto = convertModelToDTO(cardModelOptional.get());

		return cardModelOptional;

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
