package com.gravata.netconsul.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gravata.netconsul.R;
import com.gravata.netconsul.Utils;
import com.gravata.netconsul.model.Planilha;

import java.util.List;

/**
 * Created by bruno on 17/10/15.
 */
public class PlanilhaRespostaAdapter extends ArrayAdapter<Planilha> {

    private LayoutInflater mInflater;
    private Integer corOriginal;

    public PlanilhaRespostaAdapter(Context context, List<Planilha> itens) {
        super(context, 0,itens);
        init(context);
    }

    private void init(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.linha_resposta_planilha, parent, false);

        if(corOriginal==null)
            corOriginal= Utils.getOriginalColor(convertView);

        Planilha item=getItem(position);


        TextView indice = (TextView) convertView.findViewById(R.id.planilha_resposta_indice);
        TextView pergunta = (TextView) convertView.findViewById(R.id.planilha_resposta_pergunta);
        MultiAutoCompleteTextView acao = (MultiAutoCompleteTextView) convertView.findViewById(R.id.planilha_resposta_acao);
        RadioGroup radios = (RadioGroup) convertView.findViewById(R.id.planilha_resposta_radios);

        indice.setText(Integer.toString(position + 1));
        pergunta.setText(item.getPergunta().getNomePergunta());
        acao.setText(item.getAcaoCorretiva());

        if(item.getProtocolo()==1)
            radios.check(R.id.planilha_resposta_sim);
        else if(item.getProtocolo()==0)
            radios.check(R.id.planilha_resposta_nao);


        if (position % 2 == 0)
            convertView.setBackgroundColor(convertView.getResources().getColor(android.R.color.background_light));
        else
            convertView.setBackgroundColor(corOriginal);

        return convertView;
    }


}
