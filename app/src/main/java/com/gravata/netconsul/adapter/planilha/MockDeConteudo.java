package com.gravata.netconsul.adapter.planilha;

import com.gravata.netconsul.model.Atendimento;
import com.gravata.netconsul.model.Cliente;
import com.gravata.netconsul.model.Pergunta;
import com.gravata.netconsul.model.Planilha;
import com.gravata.netconsul.model.Temperatura;
import com.gravata.netconsul.model.TipoPergunta;
import com.gravata.netconsul.model.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MockDeConteudo {


    public static List<Cliente> CLIENTES = new ArrayList<Cliente>();
    public static List<Usuario> USUARIOS = new ArrayList<Usuario>();
    public static List<Pergunta> PERGUNTAS = new ArrayList<Pergunta>();
    public static List<Temperatura> TEMPERATURAS = new ArrayList<Temperatura>();
    public static List<TipoPergunta> TIPOSPERGUNTAS = new ArrayList<TipoPergunta>();

    public static List<Atendimento> ATENDIMENTOS = new ArrayList<Atendimento>();

    public static List<Planilha> PLANILHAS = new ArrayList<Planilha>();

    static {

        //monta os clientes
        for (int i = 0; i < 10; i++) {
            Cliente cliente = new Cliente();
            cliente.setCnpj(String.format("%s0000000000000", i + 1));
            cliente.setNomeFantasia(String.format("Nome fantasia %s", i + 1));
            cliente.setTelefone(String.format("%s4442222", i + 1));

            cliente.setId(i);
            CLIENTES.add(cliente);
        }

        //monta os usuarios
        for (int i = 0; i < 5; i++) {
            Usuario usuario = new Usuario();
            usuario.setEmail(String.format("%s@bla", i + 1));
            usuario.setNome(String.format("Nome %s", i + 1));
            usuario.setSenha(String.format("senha%s", i + 1));
            usuario.setId(i);
            USUARIOS.add(usuario);
        }

        //monta as perguntas
        for (int i = 0; i < 10; i++) {
            Pergunta pergunta = new Pergunta();
            pergunta.setNomePergunta(String.format("quantos mafagafos tem um ninho de mafagafo%s?", i + 1));
            pergunta.setId(i);
            PERGUNTAS.add(pergunta);
        }

        //monta os temperaturas
        for (int i = 0; i < 5; i++) {
            Temperatura temperatura = new Temperatura();
            temperatura.setDataCadastro(new Date());
            temperatura.setEquipamento(String.format("equipamento %s", i + 1));
            temperatura.setValor(String.format("%sC", i + 1));
            temperatura.setId(i);
            TEMPERATURAS.add(temperatura);
        }

        //monta as TIPOS perguntas
        for (int i = 0; i < 5; i++) {
            TipoPergunta pergunta = new TipoPergunta();
            pergunta.setNome(String.format("tipo pergunta %s?", i + 1));
            pergunta.setId(i);
            TIPOSPERGUNTAS.add(pergunta);
        }

        for (int i = 0; i < 10; i++) {
            Planilha planilha = new Planilha();

            planilha.setAcaoCorretiva(String.format("foi feito um bando de coisa %s", i + 1));
            planilha.setProtocolo(1);
            planilha.setId(i);
            PLANILHAS.add(planilha);
        }


        for (int i = 0, a = 0; i < 10; i++, a++) {
            PERGUNTAS.get(i).setTipoPergunta(TIPOSPERGUNTAS.get(a));
            if (a == 4)
                a = 0;
        }

        for (int i = 0, a = 0; i < 10; i++, a++) {
            PLANILHAS.get(i).setUsuario(USUARIOS.get(a));
            PLANILHAS.get(i).setCliente(CLIENTES.get(a));
            PLANILHAS.get(i).setPergunta(PERGUNTAS.get(a));

            if (a == 4)
                a = 0;
        }

        for (int i = 0, a = 0; i < 5; i++, a++) {
            TEMPERATURAS.get(i).setUsuario(USUARIOS.get(a));
            TEMPERATURAS.get(i).setCliente(CLIENTES.get(a));

            if (a == 4)
                a = 0;
        }

        for (Cliente cliente : CLIENTES) {

            for (Planilha planilha : PLANILHAS) {
                if (planilha.getCliente().equals(cliente))
                    cliente.getPlanilhas().add(planilha);
            }

            for(Temperatura temperatura:TEMPERATURAS){
                if(temperatura.getCliente().equals(cliente))
                    cliente.getTemperaturas().add(temperatura);
            }
        }

        for (int i = 0; i < 5; i++) {

            Usuario usuario = USUARIOS.get(i);

            for (int a = 0; a < 10; a++) {
                Cliente cliente = CLIENTES.get(a);

                Atendimento atendimento = new Atendimento();

                atendimento.setCliente(cliente);
                atendimento.setUsuario(usuario);

                ATENDIMENTOS.add(atendimento);

            }

        }

        int i = 1;
        for (Atendimento atendimento : ATENDIMENTOS) {
            atendimento.setId(i);
            i++;
        }

        for (Usuario usuario : USUARIOS) {

            for (Planilha planilha : PLANILHAS) {
                if (planilha.getUsuario().equals(usuario))
                    usuario.getPlanilhas().add(planilha);
            }

            for (Atendimento atendimento : ATENDIMENTOS) {
                if (atendimento.getUsuario().equals(usuario))
                    usuario.getAtendimentos().add(atendimento);
            }
        }


    }


}
