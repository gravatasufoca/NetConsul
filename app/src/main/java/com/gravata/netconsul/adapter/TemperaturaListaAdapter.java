package com.gravata.netconsul.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gravata.netconsul.R;
import com.gravata.netconsul.Utils;
import com.gravata.netconsul.model.Temperatura;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by bruno on 17/10/15.
 */
public class TemperaturaListaAdapter extends ArrayAdapter<Temperatura> {

    private LayoutInflater mInflater;
    private Integer corOriginal;
    private int selecionado=-1;

    public TemperaturaListaAdapter(Context context, List<Temperatura> itens) {
        super(context, 0,itens);
        init(context);
    }

    private void init(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.linha_lista_temperatura, parent, false);

        if(corOriginal==null)
            corOriginal= Utils.getOriginalColor(convertView);

        Temperatura temperatura=getItem(position);


        TextView nome = (TextView) convertView.findViewById(R.id.lista_temperatura_nome);
        TextView data = (TextView) convertView.findViewById(R.id.lista_temperatura_data);
        TextView equipamento = (TextView) convertView.findViewById(R.id.lista_temperatura_equipamento);
        TextView valor = (TextView) convertView.findViewById(R.id.lista_temperatura_valor);

        nome.setText(temperatura.getCliente().getNomeFantasia());
        equipamento.setText(temperatura.getEquipamento());
        valor.setText(temperatura.getValor());
        data.setText( new DateTime(temperatura.getDataCadastro()).toString("dd/MM/yyyy"));

        if (position % 2 == 0)
            convertView.setBackgroundColor(convertView.getResources().getColor(android.R.color.background_light));
        else
            convertView.setBackgroundColor(corOriginal);

        if(selecionado== position){
            convertView.setBackgroundResource(android.R.color.holo_blue_light);
        }

        return convertView;
    }

    public void setSelecionado(int selecionado) {
        this.selecionado = selecionado;
    }

}
