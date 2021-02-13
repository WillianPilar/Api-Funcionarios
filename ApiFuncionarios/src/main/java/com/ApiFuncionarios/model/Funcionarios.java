package com.ApiFuncionarios.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Funcionarios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@JsonProperty("NOME")
	@Column(nullable = false, length = 100) //Para criar coluna no BD e passar as definições
	private String nome;
	
	@JsonProperty("SETOR")
	@Column(nullable = false, length = 100)
	private String setor;
	
	@JsonProperty("GESTOR")
	@Column(nullable = false, length = 100)
	private String gestor;
	
	@JsonProperty("SALARIO")
	@Column(nullable = false, length = 20)
	private String salario;
	
	@JsonProperty("VT")
	@Column(nullable = false, length = 20)
	private String valeTransporte;


	public Funcionarios(String nome, String setor, String gestor, String salario,
			String valeTransporte) {
		super();
		this.nome = nome;
		this.setor = setor;
		this.gestor = gestor;
		this.salario = salario;
		this.valeTransporte = valeTransporte;
	}

	public Funcionarios() {
		super();
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

	public String getGestor() {
		return gestor;
	}

	public void setGestor(String gestor) {
		this.gestor = gestor;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public String getValeTransporte() {
		return valeTransporte;
	}

	public void setValeTransporte(String valeTransporte) {
		this.valeTransporte = valeTransporte;
	}
}
