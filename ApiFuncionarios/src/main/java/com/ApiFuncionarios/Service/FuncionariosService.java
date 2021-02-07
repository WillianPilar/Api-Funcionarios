package com.ApiFuncionarios.Service;

import org.springframework.stereotype.Service;

import com.ApiFuncionarios.model.Funcionarios;

@Service
public interface FuncionariosService {
	
	public void saveFuncionario(Funcionarios funcionario);
}
