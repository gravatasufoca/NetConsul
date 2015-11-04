package com.gravata.netconsul.repositorio;

import com.gravata.netconsul.model.Usuario;

/**
 * Created by bruno on 02/11/15.
 */
public interface RepositorioUsuario extends Repositorio<Usuario> {

    public Usuario obterPorUsuarioSenha(String username,String password);
}
