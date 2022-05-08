package br.com.kleryton.magicthegathering.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.kleryton.magicthegathering.models.CardList;
import br.com.kleryton.magicthegathering.models.PlayerModel;
import br.com.kleryton.magicthegathering.repositories.CardsListRepository;
import br.com.kleryton.magicthegathering.repositories.PlayerRepository;
import br.com.kleryton.magicthegathering.requestDto.PlayerRequestDto;
import br.com.kleryton.magicthegathering.services.exceptions.ConflictDeDadosException;

@Service
public class PlayerService {
	
	@Autowired
	CardsListRepository cardsListRepository;

	@Autowired
	PlayerRepository playerRepository;

	@Transactional
	// Create Player
	public PlayerModel createPlayer(PlayerRequestDto playerRequestDto) {

		PlayerModel playerModel = convertDtoToModel(playerRequestDto);

		// Verifica se o player já está em uso no banco
		try {
			playerRepository.save(playerModel);
			CardList cardsList = new CardList();
			playerModel.setCardsList(cardsListRepository.save(cardsList));
		} catch (DataIntegrityViolationException e) {
			throw new ConflictDeDadosException("Player is already in use!");
		}
		return playerModel;
	}

	// Coverte um request DTO em player
	public PlayerModel convertDtoToModel(PlayerRequestDto playerRequestDto) {
		PlayerModel playerModel = new PlayerModel();
		playerModel.setName(playerRequestDto.getName());
		return playerModel;
	}

}
