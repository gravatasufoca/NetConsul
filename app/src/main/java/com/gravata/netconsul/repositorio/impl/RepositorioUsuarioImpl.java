package com.gravata.netconsul.repositorio.impl;

import android.content.Context;

import com.gravata.netconsul.model.Usuario;
import com.gravata.netconsul.repositorio.RepositorioGenerico;
import com.gravata.netconsul.repositorio.RepositorioUsuario;

import java.sql.SQLException;

/**
 * Created by bruno on 02/11/15.
 */
public class RepositorioUsuarioImpl extends RepositorioGenerico<Usuario> implements RepositorioUsuario {
    public RepositorioUsuarioImpl(Context context) throws SQLException {
        super(context);
    }

    @Override
    public Usuario obterPorUsuarioSenha(String username, String password) {

        try {
            return database.queryForFirst(database.queryBuilder().where().eq("username", username).and().eq("password", password).prepare());
        } catch (SQLException e) {
            return null;
        }
    }
}
