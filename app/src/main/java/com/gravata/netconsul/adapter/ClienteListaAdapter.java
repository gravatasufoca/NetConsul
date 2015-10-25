package com.gravata.netconsul.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gravata.netconsul.R;
import com.gravata.netconsul.Utils;
import com.gravata.netconsul.model.Cliente;

import java.util.List;

/**
 * Created by bruno on 17/10/15.
 */
public class ClienteListaAdapter extends ArrayAdapter<Cliente> {

    private LayoutInflater mInflater;
    private Integer corOriginal;
    private int selecionado=-1;

    public ClienteListaAdapter(Context context, List<Cliente> itens) {
        super(context, 0,itens);
        init(context);
    }

    private void init(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.linha_lista_cliente, parent, false);

        if(corOriginal==null)
            corOriginal= Utils.getOriginalColor(convertView);

        Cliente cliente=getItem(position);


        TextView nome = (TextView) convertView.findViewById(R.id.lista_cliente_nome);
        TextView cnpj = (TextView) convertView.findViewById(R.id.lista_cliente_cnpj);

        nome.setText(cliente.getNomeFantasia());
        cnpj.setText(cliente.getCnpj());

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
