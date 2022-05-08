package br.com.kleryton.magicthegathering.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kleryton.magicthegathering.models.PlayerModel;
import br.com.kleryton.magicthegathering.requestDto.PlayerRequestDto;
import br.com.kleryton.magicthegathering.services.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/v1/magic-the-gathering")
@Api(value = "Magic The Gathering")
//Otimiza o acesso a API
@CrossOrigin(origins = "*")
public class PlayerController {

	@Autowired
	PlayerService playerService;

	// SavePlayer
	@ApiOperation(value = "Salva um novo player")
	@PostMapping("/player/add")
	public ResponseEntity<Object> saveAccount(@RequestBody @Valid PlayerRequestDto playerRequestDto) {
		PlayerModel playerModel = playerService.createPlayer(playerRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(playerModel);
	}

}
