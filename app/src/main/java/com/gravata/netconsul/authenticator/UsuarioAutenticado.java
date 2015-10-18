package com.gravata.netconsul.authenticator;

import com.gravata.netconsul.model.Usuario;

/**
 * Created by bruno on 18/10/15.
 */
public final class UsuarioAutenticado {

    private static final UsuarioAutenticado INSTANCE = new UsuarioAutenticado();
    private Usuario usuario;

    private UsuarioAutenticado(){
    }

    public static UsuarioAutenticado getInstance(){
        return INSTANCE;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
