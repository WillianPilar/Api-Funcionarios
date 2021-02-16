package com.ApiFuncionarios.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiFuncionarios.Service.FuncionariosService;
import com.ApiFuncionarios.model.Funcionarios;
import com.ApiFuncionarios.model.dto.salarioDTO;

@RestController
@RequestMapping("/api-v1")
public class CalculosController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculosController.class);
	
	@Autowired
	FuncionariosService service;
	
	salarioDTO dto = new salarioDTO();
	
	@GetMapping("/salario/{id}")
	public ResponseEntity<salarioDTO> salarioLiquido(@PathVariable int id){
		
		LOGGER.info("-----CALCULAR SALÁRIO LÍQUIDO-----");

		LOGGER.info("1 - Buscando funcionário...");
		Optional<Funcionarios> funcionario = service.getFuncionarioById(id);
		
		if (funcionario.isPresent()) {
			
			String salarioBruto = funcionario.get().getSalario();
			String isVT = funcionario.get().getValeTransporte();
			
			LOGGER.info("2 - Funcionário encontrado.");
			
			LOGGER.info("3 - Iniciando calculos sobre o salário");
			salarioLiquido(salarioBruto, isVT);
			
			dto.setNome(funcionario.get().getNome());
			
		} else {
			dto = null;
			LOGGER.info("2 - Funcionário não foi encontrado.");
			
			return new ResponseEntity<salarioDTO>(dto, HttpStatus.NOT_FOUND);
		}
		
		LOGGER.info("Calculos feitos com sucesso.");
		return new ResponseEntity<salarioDTO>(dto, HttpStatus.OK);
	}
	
	public salarioDTO salarioLiquido(String salarioBruto, String isVT) {
			
			double salario = Double.valueOf(salarioBruto);
			double vt;
			double salarioLiquido;
			double inss = descontoINSS(salario);
			
			if (isVT.equalsIgnoreCase("SIM")) {
				vt = descontoVT(salario);
				dto.setDescontoVT(vt);
				
				salarioLiquido = salario - inss - vt;
			}else {
				vt = 0;
				dto.setDescontoVT(vt);
				
				salarioLiquido = salario - inss;
			}
			
			dto.setDescontoINSS(inss);
			dto.setSalario(salario);
			dto.setSalarioLiquido(salarioLiquido);
			
			return dto;
			
		}

	private double descontoINSS(Double salario) {
		LOGGER.info("4 - Calculando desconto do INSS...");
		
		double descontoTotal;
		
		if(salario <= 1100) {
			descontoTotal = salario * 0.075;
		}else if(salario >= 1100.01 && salario <= 2203.45) {
			descontoTotal =  salario * 0.09;
		}else if(salario >= 2203.46 && salario <= 3305.22) {
			descontoTotal = salario * 0.12;
		}else {
			descontoTotal = salario * 0.14;
		}
		
		return descontoTotal;
	}
	
	private double descontoVT(double salario) {
		LOGGER.info("5 - Calculando desconto do Vale Transporte");
		double descontoVT = salario * 0.06;
		
		return descontoVT;
	}

}
