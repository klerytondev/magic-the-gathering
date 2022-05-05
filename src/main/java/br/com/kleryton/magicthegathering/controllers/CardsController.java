package br.com.kleryton.magicthegathering.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/v1/magic-the-gathering")
@Api(value="Magic The Gathering")
//Otimiza o acesso a API
@CrossOrigin(origins="*")
public class CardsController {

}
