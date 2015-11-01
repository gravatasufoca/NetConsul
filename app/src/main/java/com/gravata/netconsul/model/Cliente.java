package com.gravata.netconsul.model;

import com.gravata.netconsul.helper.FieldName;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 11/10/15.
 */
@DatabaseTable(tableName = "cliente")
public class Cliente extends EntidadeAbstrata {


    private static final long serialVersionUID = 6984432176556892784L;

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull=true,columnName = "nome_fantasia")
    @FieldName(fieldName="nomeFantasia")
    private String nomeFantasia;

    @DatabaseField(canBeNull=true,columnName = "cnpj")
    @FieldName(fieldName="cnpj")
    private String cnpj;

    @DatabaseField(canBeNull=true,columnName = "telefone")
    @FieldName(fieldName="telefone")
    private String telefone;

    @ForeignCollectionField(eager = false)
    private ForeignCollection<Planilha> planilhas;

    @ForeignCollectionField(eager = false)
    private ForeignCollection<Temperatura> temperaturas;


    @Override
    public Serializable getId() {
        return id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ForeignCollection<Planilha> getPlanilhas() {
        return planilhas;
    }

    public void setPlanilhas(ForeignCollection<Planilha> planilhas) {
        this.planilhas = planilhas;
    }

    public ForeignCollection<Temperatura> getTemperaturas() {
        return temperaturas;
    }

    public void setTemperaturas(ForeignCollection<Temperatura> temperaturas) {
        this.temperaturas = temperaturas;
    }
}
