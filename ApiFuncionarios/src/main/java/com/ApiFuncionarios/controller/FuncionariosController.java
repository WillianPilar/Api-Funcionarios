package com.ApiFuncionarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiFuncionarios.Service.FuncionariosService;
import com.ApiFuncionarios.model.Funcionarios;

@RestController
@RequestMapping("/api-v1")
public class FuncionariosController {
	
	@Autowired
	Funcionarios funcionarios;
	
	@Autowired
	FuncionariosService service;
	
	@PostMapping("/new-funcionario")
	public ResponseEntity<String> saveFuncionario(@RequestBody String nome, @RequestBody String setor){
		
		
		try {
			funcionarios.setNome(nome);
			funcionarios.setSetor(setor);
			
			service.saveFuncionario(funcionarios);
		} catch (Exception e) {
			return null; 
		}
		
		return ResponseEntity.ok("Funcionário criado.");
		
	}
}
