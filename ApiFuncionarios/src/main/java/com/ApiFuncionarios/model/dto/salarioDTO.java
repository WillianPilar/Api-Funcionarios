package com.ApiFuncionarios.model.dto;

public class salarioDTO {
	
	String nome;
	double salario;
	double salarioLiquido;
	double descontoVT;
	double descontoINSS;
	
	public salarioDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}

	public double getDescontoVT() {
		return descontoVT;
	}

	public void setDescontoVT(double descontoVT) {
		this.descontoVT = descontoVT;
	}

	public double getDescontoINSS() {
		return descontoINSS;
	}

	public void setDescontoINSS(double descontoINSS) {
		this.descontoINSS = descontoINSS;
	}

}
