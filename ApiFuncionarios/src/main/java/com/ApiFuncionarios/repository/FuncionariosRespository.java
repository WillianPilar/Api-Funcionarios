package com.ApiFuncionarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ApiFuncionarios.model.Funcionarios;

public interface FuncionariosRespository extends JpaRepository<Funcionarios, Integer>{
	
}
