package br.com.kleryton.magicthegathering.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.kleryton.magicthegathering.models.CardsModel;
import br.com.kleryton.magicthegathering.repositories.CardsRepository;
import br.com.kleryton.magicthegathering.requestDto.CardsRequestDto;
import br.com.kleryton.magicthegathering.services.exceptions.ConflictDeDadosException;

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
