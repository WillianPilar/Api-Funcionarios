package com.ApiFuncionarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ApiFuncionarios.model.Funcionarios;

public interface FuncionariosRepository extends JpaRepository<Funcionarios, Integer> {

}
