package com.ApiFuncionarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ApiFuncionarios.model.Funcionarios;

public interface FuncionariosRepository extends JpaRepository<Funcionarios, Integer> {
	
	public List<Funcionarios> findBySetor(String setor);

}
