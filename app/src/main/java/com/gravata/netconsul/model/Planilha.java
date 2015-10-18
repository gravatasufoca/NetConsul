package com.gravata.netconsul.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bruno on 11/10/15.
 */
@DatabaseTable(tableName = "planilha")
public class Planilha extends EntidadeAbstrata {
    private static final long serialVersionUID = -6892091644675400424L;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull=true,foreign=true,foreignAutoRefresh=true,foreignAutoCreate=true)
    private Usuario usuario;

    @DatabaseField(canBeNull=true,foreign=true,foreignAutoRefresh=true,foreignAutoCreate=true)
    private Cliente cliente;

    @DatabaseField(canBeNull=true,foreign=true,foreignAutoRefresh=true,foreignAutoCreate=true)
    private Pergunta pergunta;

    @DatabaseField(canBeNull=true,columnName = "id_protocolo")
    private int protocolo;

    @DatabaseField(canBeNull=true,columnName = "id_resposta")
    private int resposta;

    @DatabaseField(canBeNull=true,columnName = "ocorrencia")
    private String ocorrencia;

    @DatabaseField(canBeNull=true,columnName = "acao_corretiva")
    private String acaoCorretiva;

    @DatabaseField(canBeNull=true,columnName = "observacao")
    private String observacao;

    @DatabaseField(canBeNull=true,columnName = "data_cadastro",dataType = DataType.DATE_LONG)
    private Date dataCadastro;




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

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public int getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(int protocolo) {
        this.protocolo = protocolo;
    }

    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }

    public String getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(String ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public String getAcaoCorretiva() {
        return acaoCorretiva;
    }

    public void setAcaoCorretiva(String acaoCorretiva) {
        this.acaoCorretiva = acaoCorretiva;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
