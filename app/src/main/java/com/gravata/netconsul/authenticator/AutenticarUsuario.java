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

    public static boolean autenticar(Context context,String usuario,String senha){

        try {
            RepositorioUsuario repositorioUsuario=new RepositorioUsuarioImpl(context);

            Usuario user = repositorioUsuario.obterPorUsuarioSenha(usuario,senha);

            if(user!=null) {
                UsuarioAutenticado.getInstance().setUsuario(user);
                return true;
            }
            else {
                UsuarioAutenticado.getInstance().setUsuario(null);
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
