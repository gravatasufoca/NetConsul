package com.gravata.netconsul.authenticator;

import com.gravata.netconsul.adapter.planilha.DummyContent;

/**
 * Created by bruno on 18/10/15.
 */
public class AutenticarUsuario {

    public static void autenticar(String usuario,String senha){

        UsuarioAutenticado.getInstance().setUsuario(DummyContent.USUARIOS.get(0));
    }

}
