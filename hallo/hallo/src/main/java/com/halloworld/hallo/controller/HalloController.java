package com.halloworld.hallo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Indicar que é uma classe de Controller
@RestController
//Indicar o Caminho para acessar esse ponto de acesso
@RequestMapping("/hallo")


public class HalloController {

	//metodo para aparecer ao utilizar o metodo Get
	@GetMapping
		public String hello() {
		return "Hello Generation!!!";
	}

}
