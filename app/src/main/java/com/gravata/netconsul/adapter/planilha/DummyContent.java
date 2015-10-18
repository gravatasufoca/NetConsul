package com.gravata.netconsul.adapter.planilha;

import com.gravata.netconsul.model.Cliente;
import com.gravata.netconsul.model.Pergunta;
import com.gravata.netconsul.model.Planilha;
import com.gravata.netconsul.model.Temperatura;
import com.gravata.netconsul.model.TipoPergunta;
import com.gravata.netconsul.model.Usuario;

import java.util.ArrayList;
import java.util.List;


public class DummyContent {


    public static List<Cliente> CLIENTES =new ArrayList<Cliente>();
    public static List<Usuario> USUARIOS =new ArrayList<Usuario>();
    public static List<Pergunta> PERGUNTAS =new ArrayList<Pergunta>();
    public static List<Temperatura> TEMPERATURAS =new ArrayList<Temperatura>();
    public static List<TipoPergunta> TIPOSPERGUNTAS =new ArrayList<TipoPergunta>();


    public static List<Planilha> PLANILHAS =new ArrayList<Planilha>();

    static {
        //monta os clientes
        for(int i=0;i<5;i++){
            Cliente cliente=new Cliente();
            cliente.setCnpj(String.format("%s0000000000000", i + 1));
            cliente.setNomeFantasia(String.format("Nome fantasia %s", i + 1));
            cliente.setTelefone(String.format("%s4442222", i + 1));

            cliente.setId(i);
            CLIENTES.add(cliente);
        }

        //monta os usuarios
        for(int i=0;i<5;i++){
            Usuario usuario=new Usuario();
            usuario.setEmail(String.format("%s@bla", i + 1));
            usuario.setNome(String.format("Nome %s", i + 1));
            usuario.setSenha(String.format("senha%s", i + 1));
            usuario.setId(i);
            USUARIOS.add(usuario);
        }

        //monta as perguntas
        for(int i=0;i<10;i++){
            Pergunta pergunta = new Pergunta();
            pergunta.setNomePergunta(String.format("quantos mafagafos tem um ninho de mafagafo%s?", i + 1));
            pergunta.setId(i);
            PERGUNTAS.add(pergunta);
        }

        //monta as TIPOS perguntas
        for(int i=0;i<5;i++){
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


       for(int i=0,a=0;i<10;i++,a++){
           PERGUNTAS.get(i).setTipoPergunta(TIPOSPERGUNTAS.get(a));
           if(a==4)
               a=0;
       }

        for(int i=0,a=0;i<10;i++,a++){
            PLANILHAS.get(i).setUsuario(USUARIOS.get(a));
            PLANILHAS.get(i).setCliente(CLIENTES.get(a));
            PLANILHAS.get(i).setPergunta(PERGUNTAS.get(a));

            if(a==4)
                a=0;
        }
    }

}
