package com.ApiFuncionarios.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ApiFuncionarios.Service.Impl.FuncionariosServiceImpl;
import com.ApiFuncionarios.model.Funcionarios;

@RestController
@RequestMapping("/api-v1")
public class FuncionariosController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FuncionariosController.class);
	
	@Autowired
	private FuncionariosServiceImpl service;
	
	@PostMapping("/new")
	public ResponseEntity<Funcionarios> saveFuncionario(@RequestBody Funcionarios funcionarios){
		
		Funcionarios newFuncionario;
		LOGGER.info("Recebendo dados do funcionário...");
		
		try {
			
			LOGGER.info("Salvando dados do funcionário...");
			newFuncionario = service.saveFuncionario(funcionarios);
			LOGGER.info("Dados do Funcionário salvos!");
			
		} catch (Exception e) {
			
			LOGGER.info("ERRO ao salvar dados do funcionário... {}", e);
			newFuncionario = null;
			return new ResponseEntity<Funcionarios>(newFuncionario, HttpStatus.EXPECTATION_FAILED);
			
		}
		
		return new ResponseEntity<Funcionarios>(newFuncionario, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<Optional<Funcionarios>> getById(@PathVariable int id){
		
		Optional<Funcionarios> funcionario;
			
		LOGGER.info("Procurando funciorário pelo ID...");
		funcionario = service.getFuncionarioById(id);
		
		if (funcionario.isPresent()) {
			
			LOGGER.info("Funcionário encontrado com sucesso!");
			
		}else {
			LOGGER.info("Funcionário não foi encontrado.");
			funcionario = null;

			return new ResponseEntity<Optional<Funcionarios>>(funcionario, HttpStatus.NOT_FOUND);
			
		}
		
		return new ResponseEntity<Optional<Funcionarios>>(funcionario, HttpStatus.OK);
		
	}
	
	@GetMapping("/search/all")
	public ResponseEntity<List<Funcionarios>> getAll(){
		
		List<Funcionarios> listFuncionarios = new ArrayList<>();
		listFuncionarios = service.getListFuncionarios();
		
		try {
			
			LOGGER.info("Procurando funcionários...");
			
			
			LOGGER.info("Funcionários encontrados!");
			
		} catch (Exception e) {
			
			LOGGER.info("Algo de errado aconteceu e os Funcionários não foram encontrados. {}", e);
			listFuncionarios = null;
			
			return new ResponseEntity<List<Funcionarios>>(listFuncionarios, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Funcionarios>>(listFuncionarios, HttpStatus.OK);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Funcionarios> updateFuncionario(@PathVariable int id, @RequestBody Funcionarios update){
		
		LOGGER.info("Pesquisando funcionário...");
		Optional<Funcionarios> funcionario = service.getFuncionarioById(id);
		
		if(funcionario.isPresent()){
			
			LOGGER.info("Funcionário exites. Atualizando Funcionário...");
			update.setId(id);
			service.updateFuncionarioById(update);
		
		}else {
			
			update = null;
			LOGGER.info("Funcionário não foi encontrado.");
			return new ResponseEntity<Funcionarios>(update, HttpStatus.NOT_FOUND);
			
		}
		
		return new ResponseEntity<Funcionarios>(update, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteFuncionario(@PathVariable int id){
		
		LOGGER.info("Pesquisando funcionário...");
		Optional<Funcionarios> funcionario = service.getFuncionarioById(id);
		
		if(funcionario.isPresent()) {
			
			LOGGER.info("Funcionário encontrado. Deletando funcionário...");
			
			service.deleteById(id);
			
			LOGGER.info("Funcionário deletado.");
			
		}else {
			
			LOGGER.info("Funcionário não encontrado.");
			return new ResponseEntity<String>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
			
		}
		
		return new ResponseEntity<String>("Funcionário "+ funcionario.get().getNome() + " foi deletado.", HttpStatus.OK);
	}
	
	@GetMapping("/search/setor")
	public ResponseEntity<List<Funcionarios>> getSetor(@RequestParam String setor){
		
		List<Funcionarios> funcionariosDoSetor = service.findBySetor(setor);
		
		return new ResponseEntity<List<Funcionarios>>(funcionariosDoSetor,  HttpStatus.OK);
	}

}
