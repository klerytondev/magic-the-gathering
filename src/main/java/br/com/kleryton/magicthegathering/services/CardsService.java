package br.com.kleryton.magicthegathering.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.kleryton.bankingsystem.models.AccountModel;
import br.com.kleryton.bankingsystem.requestDto.AccountRequestDto;
import br.com.kleryton.bankingsystem.services.exceptions.ConflictDeDadosException;
import br.com.kleryton.magicthegathering.models.CardsModel;
import br.com.kleryton.magicthegathering.repositories.CardsRepository;

@Service
public class CardsService {
	
	@Autowired
	CardsRepository cardsRepository;

	@Transactional
	// Create card
	public CardsModel create(AccountRequestDto accountRequestDto) {

		AccountModel accountModel = convertDtoToModel(accountRequestDto);

		// Verifica se accountCode ou RegisteId já está em uso no banco
		try {
			accountRepository.save(accountModel);
		} catch (DataIntegrityViolationException e) {
			throw new ConflictDeDadosException("accoundCode or RegisterId is already in use!");
		}
		return accountModel;
	}
}
