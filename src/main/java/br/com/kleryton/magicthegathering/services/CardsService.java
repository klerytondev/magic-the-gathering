package br.com.kleryton.magicthegathering.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.kleryton.bankingsystem.models.AccountModel;
import br.com.kleryton.bankingsystem.requestDto.AccountRequestDto;
import br.com.kleryton.bankingsystem.responseDto.AccountResponseDto;
import br.com.kleryton.bankingsystem.services.exceptions.IntegridadeDeDadosException;
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
		public AccountResponseDto updateAcoount(Long id, AccountRequestDto accountRequestDto) {

			// Busca no banco de dados se existe account com o id passado
			Optional<AccountModel> accountModelOptional = accountRepository.findById(id);
			accountModelOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Account not found."));

			// Atualiza os campos da account existentes

			accountModelOptional.get().setNameOwner(accountRequestDto.getNameOwner());
			accountModelOptional.get().setAgencyCode(accountRequestDto.getAgencyCode());
			accountModelOptional.get().setAccountCode(accountRequestDto.getAccountCode());
			accountModelOptional.get().setVerificationDigital(accountRequestDto.getVerificationDigital());
			accountModelOptional.get().setRegisterId(accountRequestDto.getRegisterId());

			// Verifica se accountCode ou RegisteId já está em uso no banco
			try {
				accountRepository.save(accountModelOptional.get());
			} catch (Exception e) {
				throw new DataIntegrityViolationException("accoundCode or RegisterId is already in use!");
			}

			AccountResponseDto accountResponseDto = convertModelToDTO(accountModelOptional.get());

			return accountResponseDto;

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
