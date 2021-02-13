package com.ApiFuncionarios.Service;

import java.util.List;
import java.util.Optional;

import com.ApiFuncionarios.model.Funcionarios;

public interface FuncionariosService {
	
	public Funcionarios saveFuncionario(Funcionarios funcionario);
	
	public Optional<Funcionarios> getFuncionarioById(int id);

	public List<Funcionarios> getListFuncionarios();
	
	public Funcionarios updateFuncionarioById(Funcionarios update);
	
	public void deleteById(int id);
	
	public List<Funcionarios> findBySetor(String setor);
}
