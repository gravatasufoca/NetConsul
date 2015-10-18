package com.gravata.netconsul.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by bruno on 11/10/15.
 */
@DatabaseTable(tableName = "temperaturas")
public class Temperatura extends EntidadeAbstrata{
    private static final long serialVersionUID = 2276186839862129237L;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false,foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    private Usuario usuario;

    @DatabaseField(canBeNull = false,foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    private Cliente cliente;

    @ForeignCollectionField(eager = false)
    private ForeignCollection<TemperaturaCliente> temperaturaClientes;

    @Override
    public Serializable getId() {
        return id;
    }

    public void setId(int id) {
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
}
