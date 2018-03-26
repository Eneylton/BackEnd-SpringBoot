package com.alunos.dto;

import java.io.Serializable;

import com.alunos.model.Aluno;

public class AlunoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private String endereco;

	private String email;

	private String telefone;

	public AlunoDTO() {
		super();
	}

	public AlunoDTO(Aluno obj) {
		id = obj.getId();
		nome = obj.getNome();
		endereco = obj.getEmail();
		email = obj.getNome();
		telefone = obj.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
