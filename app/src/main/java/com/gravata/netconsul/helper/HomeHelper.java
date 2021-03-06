package com.gravata.netconsul.helper;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.gravata.netconsul.R;
import com.gravata.netconsul.adapter.DataAdapter;
import com.gravata.netconsul.dao.OrmLiteFragment;
import com.gravata.netconsul.repositorio.RepositorioGenerico;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.ganfra.materialspinner.MaterialSpinner;
import tools.devnull.trugger.reflection.Reflection;

public class HomeHelper<E> extends OrmLiteFragment {

	protected Class<E> clazz;
	protected E instance;
	Map<Field,MapField> fields=new HashMap<Field, MapField>();
	private List<Field> requireds;
	private RepositorioGenerico<E> repositorioGenerico;

	public HomeHelper() {
		this.clazz = Reflection.reflect().genericType("E").in(this);
		parse();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			repositorioGenerico=new RepositorioGenerico<E>(this.getActivity(),clazz);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public E getInstance() {
		if(instance==null){
			try {
				instance=clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (java.lang.InstantiationException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	protected void setInstance(E instance) {
		this.instance = instance;
	}

	protected void setId(Serializable id){
		if(id!=null){
			setInstance(repositorioGenerico.obterPorId(id));
		}
	}

	protected boolean save() throws SQLException{
		try{
			repositorioGenerico.inserir(getInstance());
			return true;
		}catch(Exception e){
			return false;
		}
	}

	protected void update() throws SQLException{
		repositorioGenerico.atualizar(getInstance());
	}

	private void parse() {

		Field fieldlist[] = clazz.getDeclaredFields();
		for (int i = 0; i < fieldlist.length; i++) {
			Field fld = fieldlist[i];
			if (fld.isAnnotationPresent(FieldName.class)) {
				if(fld.isAnnotationPresent(DatabaseField.class)){

					DatabaseField annotation = fld.getAnnotation(DatabaseField.class);
					FieldName f=fld.getAnnotation(FieldName.class);

					String edit = f.fieldName();

					MapField m=new MapField();
					m.setRequired(!annotation.canBeNull());
					m.setField(getField(edit));
					m.setDbField(fld);
					fld.setAccessible(true);
					fields.put(fld,m);
				}

			}
		}
	}



	private Field getField(String name) {
		Field field;
		try {
			field = this.getClass().getDeclaredField(name);
			field.setAccessible(true);
			return field;

		} catch (NoSuchFieldException e) {

		} catch (IllegalArgumentException e) {
		}
		return null;
	}

	private Object getValue(Field field){
		try {
			field.setAccessible(true);
			return field.get(this);
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}
		return null;
	}

	private List<Field> getRequireds(){

		if(requireds==null){
			requireds=new ArrayList<Field>();
			ArrayList<MapField> tmp=new ArrayList<MapField>(fields.values());

			for(MapField fe:tmp){
				if(fe.isRequired()){
					requireds.add(fe.getField());
				}
			}
		}
		return requireds;
	}

	public List<String> getInvalids()  {

		List<String> invalids=new ArrayList<String>();

		List<Field> requireds=getRequireds();


		for(Field field:requireds){
			try{
				Object value=getValue(field);

				if(value instanceof TextView){
					TextView tmp=(TextView)value;

					if(tmp.getText().length()==0){
						invalids.add(field.getName());
						if(value instanceof AutoCompleteTextView){
							((AutoCompleteTextView)value).setError(getString(R.string.error_field_required));
						}
					}
				}else if(value instanceof Spinner){
					Spinner tmp=(Spinner)value;
					if(tmp.getSelectedItem()==null || tmp.getAdapter().getItem(0).equals(tmp.getSelectedItem()) ){
						invalids.add(field.getName());
						if(value.getClass().isAssignableFrom(MaterialSpinner.class))
							((MaterialSpinner)value).setError(getString(R.string.error_field_required));
					}
//					if(tmp.getAdapter() instanceof CategoriaSpinnerAdapter && tmp.getSelectedItemPosition()==0){
//						invalids.add(field.getName());
//					}
				}
			}catch(Exception e){}
		}

		return invalids;
	}

	public boolean isValid() {
		try {
			updateValues();
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}
		return getInvalids().size()==0;
	}

	protected void updateValues() throws IllegalArgumentException, IllegalAccessException{
		ArrayList<MapField> fields=new ArrayList<MapField>(this.fields.values());
		getInstance();
		for(MapField field:fields){
			if(field.getField()!=null){
				Object value=getValue(field.getField());

				if(value instanceof TextView){

					if(value instanceof CompoundButton){
						CompoundButton tmp= (CompoundButton)value;
						field.getDbField().set(instance, tmp.isChecked());
					}else{
						TextView tmp=(TextView)value;
						try{
							double valor=NumberFormat.getCurrencyInstance().parse(tmp.getText().toString()).doubleValue();

							field.getDbField().set(instance, valor);
						}catch(ParseException e){
							field.getDbField().set(instance, tmp.getText().toString());
						}
					}
				}
 				else if(value instanceof Spinner){
					Spinner tmp=(Spinner)value;

					if(tmp.getAdapter() instanceof DataAdapter){
						Date at;
						try {
							at = new SimpleDateFormat("dd/MM/yyyy").parse((String) tmp.getSelectedItem());
							field.getDbField().set(instance, at);
						} catch (ParseException e) {}
					}else
						field.getDbField().set(instance, tmp.getSelectedItem());
				}
			}
		}

	}

	private class MapField{

		private Field field;
		private boolean required;
		private Field dbField;

		public Field getField() {
			return field;
		}
		public void setField(Field field) {
			this.field = field;
		}
		public boolean isRequired() {
			return required;
		}
		public void setRequired(boolean required) {
			this.required = required;
		}

		public Field getDbField() {
			return dbField;
		}
		public void setDbField(Field dbField) {
			this.dbField = dbField;
		}
	}
}
