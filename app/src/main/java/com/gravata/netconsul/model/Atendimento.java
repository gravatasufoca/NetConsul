package com.gravata.netconsul.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bruno on 18/10/15.
 */
public class Atendimento extends EntidadeAbstrata {


    private static final long serialVersionUID = -6183742655769109824L;

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull=false,foreign=true,foreignAutoRefresh=true,foreignAutoCreate=true)
    private Usuario usuario;

    @DatabaseField(canBeNull=false,foreign=true,foreignAutoRefresh=true,foreignAutoCreate=true)
    private Cliente cliente;

    @DatabaseField(columnName = "ds_endereco")
    private String endereco;

    @DatabaseField(columnName = "ds_latitude")
    private String latitude;

    @DatabaseField(columnName = "ds_longitude")
    private String longitude;

    @DatabaseField(columnName = "dt_cadastro",dataType = DataType.DATE_LONG)
    private Date dataCadastro;

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
