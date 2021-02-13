package com.ApiFuncionarios.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApiFuncionarios.Service.FuncionariosService;
import com.ApiFuncionarios.model.Funcionarios;
import com.ApiFuncionarios.repository.FuncionariosRepository;

@Service
public class FuncionariosServiceImpl implements FuncionariosService{
	
	@Autowired
	private FuncionariosRepository repository;
	
	@Override
	public Funcionarios saveFuncionario(Funcionarios funcionario) {
		
		return repository.save(funcionario);
		
	}

}
