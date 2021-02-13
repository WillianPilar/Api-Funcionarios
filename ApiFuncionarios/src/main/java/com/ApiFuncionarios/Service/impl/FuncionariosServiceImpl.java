package com.ApiFuncionarios.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	public Optional<Funcionarios> getFuncionarioById(int id) {
		
		return repository.findById(id);
	}

	public List<Funcionarios> getListFuncionarios() {
		
		Iterable<Funcionarios> func = repository.findAll();
		
		List<Funcionarios> listFunc =  new ArrayList<>();
		
		for (Funcionarios funcionario : func) {
			listFunc.add(funcionario);
		}
		
		return listFunc;
		
	}

	public Funcionarios updateFuncionarioById(Funcionarios update) {
		
		repository.save(update);
		
		return update;
		
	}

	public void deleteById(int id) {
		
		repository.deleteById(id);
		
	}
	
	public List<Funcionarios> findBySetor(String setor){
		
		return repository.findBySetor(setor);
		 
	}

}
