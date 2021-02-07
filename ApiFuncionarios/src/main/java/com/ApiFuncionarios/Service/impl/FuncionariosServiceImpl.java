package com.ApiFuncionarios.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApiFuncionarios.Service.FuncionariosService;
import com.ApiFuncionarios.model.Funcionarios;
import com.ApiFuncionarios.repository.FuncionariosRespository;

@Service
public class FuncionariosServiceImpl implements FuncionariosService{
	
	@Autowired
	FuncionariosRespository repository;
	
	@Override
	public void saveFuncionario(Funcionarios funcionario) {
		
		repository.save(funcionario);

	}

}
