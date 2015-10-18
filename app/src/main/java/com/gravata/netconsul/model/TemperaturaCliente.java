package com.gravata.netconsul.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by bruno on 11/10/15.
 */
@DatabaseTable(tableName = "temperatura_cliente")
public class TemperaturaCliente  extends  EntidadeAbstrata{
    private static final long serialVersionUID = 6119790427367081604L;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "id_temperatura",canBeNull = false,foreignAutoRefresh =true,foreignAutoCreate = true,foreign = true)
    private Temperatura temperatura;

    @DatabaseField(columnName = "equipamento",canBeNull = false)
    private String equipamento;
    @DatabaseField(columnName = "temperatura",canBeNull = false)
    private String dsTemperatura;

    @DatabaseField(columnName = "observacao",canBeNull = false)
    private String observacao;

    @DatabaseField(columnName = "acao",canBeNull = false)
    private String acao;

    @Override
    public Serializable getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getDsTemperatura() {
        return dsTemperatura;
    }

    public void setDsTemperatura(String dsTemperatura) {
        this.dsTemperatura = dsTemperatura;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }
}
