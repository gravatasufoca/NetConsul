package com.gravata.netconsul.authenticator;

import com.gravata.netconsul.adapter.planilha.MockDeConteudo;

/**
 * Created by bruno on 18/10/15.
 */
public class AutenticarUsuario {

    public static void autenticar(String usuario,String senha){

        UsuarioAutenticado.getInstance().setUsuario(MockDeConteudo.USUARIOS.get(0));
    }

}
