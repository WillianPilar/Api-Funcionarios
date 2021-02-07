package com.ApiFuncionarios.model;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Funcionarios {
	
	private String nome;
	private String setor;
	private String gestorDoFuncionario;
	private Double salarioBruto;
	private boolean valeTransporte;
	
	public Funcionarios(String nome, String setor, String gestorDoFuncionario, Double salarioBruto,
			boolean valeTransporte) {
		this.setNome(nome);
		this.setSetor(setor);
		this.setGestorDoFuncionario(gestorDoFuncionario);
		this.setSalarioBruto(salarioBruto);
		this.setValeTransporte(valeTransporte);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getGestorDoFuncionario() {
		return gestorDoFuncionario;
	}

	public void setGestorDoFuncionario(String gestorDoFuncionario) {
		this.gestorDoFuncionario = gestorDoFuncionario;
	}

	public Double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(Double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public boolean isValeTransporte() {
		return valeTransporte;
	}

	public void setValeTransporte(boolean valeTransporte) {
		this.valeTransporte = valeTransporte;
	}
	
}
