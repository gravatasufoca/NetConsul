package com.gravata.netconsul.adapter;

import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.gravata.netconsul.fragments.DatePickerFragment;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class DataAdapter extends ArrayAdapter<String> {

	private Activity activity;
	private DateTime minimo;
	private DateTime dataSelecionada;
	private boolean soDia=false;

	private Spinner campoView;

	public DataAdapter(Context context, int textViewResourceId,List<String> list,Spinner campoView) {

		super(context, textViewResourceId, list);

		this.activity=(Activity) context;
		this.minimo=minimo;
		this.campoView=campoView;
		campoView.setAdapter(this);
	}

	@Override
	public View getDropDownView(int position, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		// return super.getView(position, convertView, parent);

		LayoutInflater inflater = activity.getLayoutInflater();
		View row = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
		final TextView item = (TextView) row.findViewById(android.R.id.text1);

		String valor="";
		try {
			valor = getItem(position);
		}catch (IndexOutOfBoundsException e){
			valor="";
		}
		if (dataSelecionada == null) {
			try {
				dataSelecionada = new DateTime(new SimpleDateFormat("dd/MM/yyyy").parse(valor).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		item.setText(valor);
		setOnClick(item);
//		item.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(final View v) {
//
//				OnDateSetListener ondate = new OnDateSetListener() {
//					@Override
//					public void onDateSet(DatePicker view, int year, int monthOfYear,
//										  int dayOfMonth) {
//						Date data = new DateTime(view.getYear(), view.getMonth(), view.getDayOfMonth(), 0, 0).toDate();
//						String d = new SimpleDateFormat(!soDia ? "dd/MM/yyyy" : "dd").format(data);
//						((TextView) v).setText(d);
//						dataSelecionada = null;
//						clear();
//						add(d);
//
//						if (campoView != null) {
//							((MaterialSpinner) campoView).setSelection(1);
//						}
//						notifyDataSetChanged();
//					}
//				};
//
//				DatePickerFragment newFragment = new DatePickerFragment();
//				newFragment.setCallBack(ondate);
//				newFragment.setMinimo(minimo);
//				newFragment.setDataSelecionada(dataSelecionada);
//				newFragment.setSoDia(soDia);
//				newFragment.show(activity.getFragmentManager(), "datePicker");
//
//			}
//		});


		return row;
	}

	private void setOnClick(View item){
		if(item!=null) {
			item.setOnClickListener(onClickListener);
		}
	}

	private View.OnClickListener onClickListener=new View.OnClickListener() {

		@Override
		public void onClick(final View v) {

			OnDateSetListener ondate = new OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
									  int dayOfMonth) {
					Date data = new DateTime(view.getYear(), view.getMonth(), view.getDayOfMonth(), 0, 0).toDate();
					String d = new SimpleDateFormat(!soDia ? "dd/MM/yyyy" : "dd").format(data);
					((TextView) v).setText(d);
					dataSelecionada = null;
					clear();
					add(d);

					if (campoView != null) {
						((MaterialSpinner) campoView).setSelection(1);
					}
					notifyDataSetChanged();
				}
			};

			DatePickerFragment newFragment = new DatePickerFragment();
			newFragment.setCallBack(ondate);
			newFragment.setMinimo(minimo);
			newFragment.setDataSelecionada(dataSelecionada);
			newFragment.setSoDia(soDia);
			newFragment.show(activity.getFragmentManager(), "datePicker");

		}
	};

	public View.OnClickListener getOnClickListener() {
		return this.onClickListener;
	}

	public void setSoDia(boolean soDia) {
		this.soDia = soDia;
	}
	public void setMinimo(DateTime minimo) {
		this.minimo = minimo;
	}

	public void setDataSelecionada(DateTime dataSelecionada) {
		this.dataSelecionada = dataSelecionada;
	}

	public void setCampoView(Spinner campoView) {
		this.campoView = campoView;
	}

	public void fixOnClick(){
		notifyDataSetChanged();
	}
}