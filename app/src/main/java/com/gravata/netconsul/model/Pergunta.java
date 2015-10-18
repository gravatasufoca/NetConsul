package com.gravata.netconsul.model;

import com.gravata.netconsul.helper.FieldName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bruno on 11/10/15.
 */
@DatabaseTable(tableName = "pergunta")
public class Pergunta extends EntidadeAbstrata{

    private static final long serialVersionUID = 8954135326351326154L;


    @DatabaseField(generatedId = true,columnName = "id_pergunta")
    private int id;

    @DatabaseField(canBeNull=true,columnName = "nome_fantasia")
    @FieldName(fieldName="nomeFantasia")
    private String nomePergunta;

    @DatabaseField(canBeNull=true,columnName = "nome_fantasia")
    @FieldName(fieldName="nomeFantasia")
    private Date dataCadastro;

    @DatabaseField(canBeNull=true,columnName = "nome_fantasia")
    @FieldName(fieldName="nomeFantasia")
    private String situacao;

    @DatabaseField(canBeNull=true,foreign=true,foreignAutoRefresh=true,foreignAutoCreate=true)
    private TipoPergunta tipoPergunta;

    @Override
    public Serializable getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomePergunta() {
        return nomePergunta;
    }

    public void setNomePergunta(String nomePergunta) {
        this.nomePergunta = nomePergunta;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public TipoPergunta getTipoPergunta() {
        return tipoPergunta;
    }

    public void setTipoPergunta(TipoPergunta tipoPergunta) {
        this.tipoPergunta = tipoPergunta;
    }
}
