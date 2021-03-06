package com.gravata.netconsul.model;

import com.gravata.netconsul.helper.FieldName;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bruno on 11/10/15.
 */
@DatabaseTable(tableName = "temperaturas")
public class Temperatura extends EntidadeAbstrata{
    private static final long serialVersionUID = 2276186839862129237L;

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false,foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    private Usuario usuario;

    @DatabaseField(canBeNull = false,foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    @FieldName(fieldName = "campoCliente")
    private Cliente cliente;

    @ForeignCollectionField(eager = false)
    private ForeignCollection<TemperaturaCliente> temperaturaClientes;

    @DatabaseField(canBeNull = false,columnName = "equipamento")
    @FieldName(fieldName = "campoEquipamento")
    private String equipamento;

    @DatabaseField(canBeNull = false,columnName = "valor")
    @FieldName(fieldName = "campoTemperatura")
    private String valor;

    @DatabaseField(canBeNull = false,columnName = "data_cadatro",dataType = DataType.DATE_LONG)
    @FieldName(fieldName = "campoData")
    private Date dataCadastro;

    @DatabaseField(canBeNull = false,columnName = "acao",dataType = DataType.ENUM_STRING)
    @FieldName(fieldName = "campoAcaoCorretiva")
    private SimNao acao;

    @Override
    public Serializable getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ForeignCollection<TemperaturaCliente> getTemperaturaClientes() {
        return temperaturaClientes;
    }

    public void setTemperaturaClientes(ForeignCollection<TemperaturaCliente> temperaturaClientes) {
        this.temperaturaClientes = temperaturaClientes;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
