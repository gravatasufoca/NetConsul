package com.gravata.netconsul.fragments;

import java.lang.reflect.Field;
import java.util.Calendar;

import org.joda.time.DateTime;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment
                             {

	OnDateSetListener ondateSet;
	private DateTime minimo;
	private DateTime dataSelecionada;
	private boolean soDia=false;
	private DatePickerDialog dialog;

	public DatePickerFragment() {}


	public void setCallBack(OnDateSetListener ondate){
		this.ondateSet=ondate;
	}

	public void setMinimo(DateTime minimo) {
		this.minimo = minimo;
	}

	public void setDataSelecionada(DateTime dataSelecionada) {
		this.dataSelecionada = dataSelecionada;
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	int year,month,day;

    	if(dataSelecionada==null){
	        final Calendar c = Calendar.getInstance();
	        year = c.get(Calendar.YEAR);
	        month = c.get(Calendar.MONTH);
	        day = c.get(Calendar.DAY_OF_MONTH);
    	}else
    	{
    		year=dataSelecionada.getYear();
    		month=dataSelecionada.getMonthOfYear()-1;
    		day=dataSelecionada.getDayOfMonth();
    	}

    	if(minimo!=null){
    		year=minimo.getYear();
    		month=minimo.getMonthOfYear()-1;
    		day=minimo.getDayOfMonth();
    	}

        // Create a new instance of DatePickerDialog and return it
        dialog= new DatePickerDialog(getActivity(), ondateSet, year, month, day);

        if(minimo!=null){
        	dialog.getDatePicker().setMinDate(minimo.getMillis());
        	filterCombo("Day");
        }

        if(soDia){
        	filterCombo("Month","Year");
        }

        return dialog;
    }

    private void filterCombo(String... campos){

    	//mostra apenas mes e ano
        try {
            Field[] datePickerDialogFields = dialog.getClass().getDeclaredFields();
            for (Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dialog);
                    Field datePickerFields[] = datePickerDialogField.getType().getDeclaredFields();
                    for (Field datePickerField : datePickerFields) {
                    	for(String campo : campos){
                    		String p="m"+campo+"Picker";
                    		String s="m"+campo+"Spinner";
	                        if (p.equals(datePickerField.getName()) || s.equals(datePickerField.getName())) {
	                            datePickerField.setAccessible(true);
	                            Object dayPicker = new Object();
	                            dayPicker = datePickerField.get(datePicker);
	                            ((View) dayPicker).setVisibility(View.GONE);
	                        }
                    	}
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    public void setSoDia(boolean soDia) {
		this.soDia = soDia;
	}
}