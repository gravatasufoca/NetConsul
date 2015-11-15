package com.gravata.netconsul.authenticator;

import android.content.Context;

import com.gravata.netconsul.model.Usuario;
import com.gravata.netconsul.repositorio.RepositorioUsuario;
import com.gravata.netconsul.repositorio.impl.RepositorioUsuarioImpl;

import java.sql.SQLException;

/**
 * Created by bruno on 18/10/15.
 */
public class AutenticarUsuario {

    public static void autenticar(Context context,String usuario,String senha) throws UsuarioInvalidoException {

        try {
            RepositorioUsuario repositorioUsuario=new RepositorioUsuarioImpl(context);

            Usuario user = repositorioUsuario.obterPorUsuarioSenha(usuario,senha);

            if(user!=null) {
                UsuarioAutenticado.getInstance().setUsuario(user);
            }
            else {
                UsuarioAutenticado.getInstance().setUsuario(null);
                throw new UsuarioInvalidoException("Usuário ou senha inválido!");
            }

        } catch (SQLException e) {
            throw new UsuarioInvalidoException("Erro na validação.");

        }
    }

    public static class UsuarioInvalidoException extends Exception{

        public UsuarioInvalidoException(String detailMessage) {
            super(detailMessage);
        }
    }

}
