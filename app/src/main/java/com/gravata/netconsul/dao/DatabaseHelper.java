package com.gravata.netconsul.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gravata.netconsul.R;
import com.gravata.netconsul.adapter.planilha.MockDeConteudo;
import com.gravata.netconsul.model.Atendimento;
import com.gravata.netconsul.model.Cliente;
import com.gravata.netconsul.model.Pergunta;
import com.gravata.netconsul.model.Planilha;
import com.gravata.netconsul.model.Temperatura;
import com.gravata.netconsul.model.TemperaturaCliente;
import com.gravata.netconsul.model.TipoPergunta;
import com.gravata.netconsul.model.Usuario;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Database helper which creates and upgrades the database and provides the DAOs for the app.
 *
 * @author bruno
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	/************************************************
	 * Suggested Copy/Paste code. Everything from here to the done block.
	 ************************************************/

	private static final String DATABASE_NAME = "netconsul.db";
	private static final int DATABASE_VERSION = 1;
	private Context context;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
		this.context=context;
	}

	/************************************************
	 * Suggested Copy/Paste Done
	 ************************************************/

	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Cliente.class);
			TableUtils.createTable(connectionSource, Temperatura.class);
			TableUtils.createTable(connectionSource, Usuario.class);
			TableUtils.createTable(connectionSource, Atendimento.class);
			TableUtils.createTable(connectionSource, TipoPergunta.class);
			TableUtils.createTable(connectionSource, Pergunta.class);
			TableUtils.createTable(connectionSource, TemperaturaCliente.class);
			TableUtils.createTable(connectionSource, Planilha.class);

			criarMock();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {

	}

	private void criarMock(){
		try {
			Dao<Cliente,Integer>  daoCliente = getDao(Cliente.class);
			Dao<Temperatura,Integer>  daoTemperatura = getDao(Temperatura.class);
			Dao<Usuario,Integer>  daoUsuairo = getDao(Usuario.class);
			Dao<Pergunta,Integer>  daoPergunta = getDao(Pergunta.class);
			Dao<Planilha,Integer>  daoPlanilha = getDao(Planilha.class);
			Dao<TipoPergunta,Integer>  daoTipoPergunta = getDao(TipoPergunta.class);
			Dao<TemperaturaCliente,Integer>  daoTemperaturaCliente = getDao(TemperaturaCliente.class);

			for(Cliente cliente: MockDeConteudo.CLIENTES){
				cliente.setId(null);
				daoCliente.create(cliente);
			}

			for(Usuario usuario: MockDeConteudo.USUARIOS){
				usuario.setId(null);
				daoUsuairo.create(usuario);
			}

			for(Pergunta pergunta: MockDeConteudo.PERGUNTAS){
				pergunta.setId(null);
				daoPergunta.create(pergunta);
			}

			for(Temperatura temperatura: MockDeConteudo.TEMPERATURAS){
				temperatura.setId(null);
				daoTemperatura.create(temperatura);
			}

			for(TipoPergunta tipo: MockDeConteudo.TIPOSPERGUNTAS){
				tipo.setId(null);
				daoTipoPergunta.create(tipo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
