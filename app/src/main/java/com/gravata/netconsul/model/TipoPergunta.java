package com.gravata.netconsul.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by bruno on 11/10/15.
 */
@DatabaseTable(tableName = "tipo_pergunta")
public class TipoPergunta extends EntidadeAbstrata {


    private static final long serialVersionUID = 1707636164113678309L;

    @DatabaseField(generatedId = true,columnName = "id_tipo_pergunta")
    private int id;

    @DatabaseField(columnName = "nome_tipo_pergunta",canBeNull = false)
    private String nome;

    @DatabaseField(columnName = "situacao_tipo_pergunta",canBeNull = false)
    private String situacao;

    @Override
    public Serializable getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
