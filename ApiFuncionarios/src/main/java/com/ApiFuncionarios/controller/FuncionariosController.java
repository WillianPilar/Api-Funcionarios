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
		
		LOGGER.info("-----SALVANDO FUNCIONÁRIO-----");
		
		Funcionarios newFuncionario;
		
		LOGGER.info("1 - Recebendo dados do funcionário...");
		
		try {
			
			LOGGER.info("2 - Salvando dados do funcionário...");
			newFuncionario = service.saveFuncionario(funcionarios);
			LOGGER.info("3 - Dados do Funcionário salvos!");
			
		} catch (Exception e) {
			
			LOGGER.info("3 - ERRO ao salvar dados do funcionário... {}", e);
			newFuncionario = null;
			return new ResponseEntity<Funcionarios>(newFuncionario, HttpStatus.EXPECTATION_FAILED);
			
		}
		
		return new ResponseEntity<Funcionarios>(newFuncionario, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<Optional<Funcionarios>> getById(@PathVariable int id){
		
		LOGGER.info("-----PROCURANDO FUNCIONÁRIO PELO ID-----");
		
		Optional<Funcionarios> funcionario;
		
		LOGGER.info("1 - Procurando funciorário pelo ID...");
		funcionario = service.getFuncionarioById(id);
		
		if (funcionario.isPresent()) {
			
			LOGGER.info("2 - Funcionário encontrado com sucesso!");
			
		}else {
			LOGGER.info("2 - Funcionário não foi encontrado.");
			funcionario = null;

			return new ResponseEntity<Optional<Funcionarios>>(funcionario, HttpStatus.NOT_FOUND);
			
		}
		
		return new ResponseEntity<Optional<Funcionarios>>(funcionario, HttpStatus.OK);
		
	}
	
	@GetMapping("/search/all")
	public ResponseEntity<List<Funcionarios>> getAll(){
		
		LOGGER.info("-----PROCURANDO TODOS OS FUNCIONÁRIOS-----");
		
		List<Funcionarios> listFuncionarios = new ArrayList<>();
		
		
		try {
			
			LOGGER.info("1 - Procurando funcionários...");
			
			listFuncionarios = service.getListFuncionarios();
			
			LOGGER.info("2 - Funcionários encontrados!");
			
		} catch (Exception e) {
			
			LOGGER.info("2 - Algo de errado aconteceu e os Funcionários não foram encontrados. {}", e);
			listFuncionarios = null;
			
			return new ResponseEntity<List<Funcionarios>>(listFuncionarios, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Funcionarios>>(listFuncionarios, HttpStatus.OK);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Funcionarios> updateFuncionario(@PathVariable int id, @RequestBody Funcionarios update){
		
		LOGGER.info("-----ATUALIZANDO FUNCIONÁRIO-----");
		
		LOGGER.info("1 - Pesquisando funcionário...");
		Optional<Funcionarios> funcionario = service.getFuncionarioById(id);
		
		if(funcionario.isPresent()){
			
			LOGGER.info("2 - Funcionário existe. Atualizando Funcionário...");
			
			update.setId(id);
			service.updateFuncionarioById(update);
			
			LOGGER.info("3 - Funcionário Atualizado.");
		
		}else {
			
			update = null;
			LOGGER.info("2 - Funcionário não foi encontrado.");
			return new ResponseEntity<Funcionarios>(update, HttpStatus.NOT_FOUND);
			
		}
		
		return new ResponseEntity<Funcionarios>(update, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteFuncionario(@PathVariable int id){
		
		LOGGER.info("-----DELETANDO FUNCIONÁRIO-----");
		
		LOGGER.info("1 - Pesquisando funcionário...");
		Optional<Funcionarios> funcionario = service.getFuncionarioById(id);
		
		if(funcionario.isPresent()) {
			
			LOGGER.info("2 - Funcionário encontrado. Deletando funcionário...");
			
			service.deleteById(id);
			
			LOGGER.info("3 - Funcionário deletado.");
			
		}else {
			
			LOGGER.info("2 - Funcionário não encontrado.");
			return new ResponseEntity<String>("funcionário não encontrado.", HttpStatus.NOT_FOUND);
			
		}
		
		return new ResponseEntity<String>("Funcionário "+ funcionario.get().getNome() + " foi deletado.", HttpStatus.OK);
	}
	
	@GetMapping("/search/setor")
	public ResponseEntity<List<Funcionarios>> getSetor(@RequestParam String setor){
		
		LOGGER.info("-----PESQUISANDO FUNCIONÁRIOS POR SETOR");
		List<Funcionarios> funcionariosDoSetor;
		
		try {
			
			LOGGER.info("1 - Pesquisando funcionários por setor...");
			
			funcionariosDoSetor = service.findBySetor(setor);
			
			LOGGER.info("2 - Funcionários do setor encontrados.");
			
		} catch (Exception e) {
			
			funcionariosDoSetor = null;
			
			LOGGER.info("2 - Funcionários do setor não foram encontrados. {}", e);
			return new ResponseEntity<List<Funcionarios>>(funcionariosDoSetor, HttpStatus.NO_CONTENT);
			
		}
		
		return new ResponseEntity<List<Funcionarios>>(funcionariosDoSetor,  HttpStatus.OK);
	}

}
