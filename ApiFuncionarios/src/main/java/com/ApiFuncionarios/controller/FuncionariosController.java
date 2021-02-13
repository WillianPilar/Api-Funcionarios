package com.ApiFuncionarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ApiFuncionarios.Service.Impl.FuncionariosServiceImpl;
import com.ApiFuncionarios.model.Funcionarios;

@RestController
@RequestMapping("/api-v1")
public class FuncionariosController {
	
	@Autowired
	private FuncionariosServiceImpl service;
	
	@PostMapping("/new")
	public ResponseEntity<Funcionarios> saveFuncionario(@RequestBody Funcionarios funcionarios){
	
		
		Funcionarios newFuncionario = service.saveFuncionario(funcionarios);
	
		return new ResponseEntity<Funcionarios>(newFuncionario, HttpStatus.CREATED);
		
	}
}
