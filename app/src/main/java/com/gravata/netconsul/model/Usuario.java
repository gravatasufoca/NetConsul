package com.gravata.netconsul.model;

import com.gravata.netconsul.helper.FieldName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "usuario")
public class Usuario extends EntidadeAbstrata{

	private static final long serialVersionUID = 1073694295692104395L;

	public Usuario() {
	}


	@DatabaseField(generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull=true,columnName = "username")
	@FieldName(fieldName="nome")
	private String nome;

	@DatabaseField(canBeNull=true,columnName = "email")
	@FieldName(fieldName="email")
	private String email;

	@DatabaseField(canBeNull=true,columnName = "password")
	@FieldName(fieldName="password")
	private String senha;


	@ForeignCollectionField(eager = false)
	private List<Planilha> planilhas=new ArrayList<Planilha>();

	@ForeignCollectionField(eager = false)
	private List<Atendimento> atendimentos=new ArrayList<Atendimento>();


	public Serializable getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Planilha> getPlanilhas() {
		return planilhas;
	}

	public void setPlanilhas(List<Planilha> planilhas) {
		this.planilhas = planilhas;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}
}
