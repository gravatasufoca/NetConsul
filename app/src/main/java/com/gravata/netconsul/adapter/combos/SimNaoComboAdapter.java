package com.gravata.netconsul.adapter.combos;

import android.content.Context;
import android.widget.TextView;

import com.gravata.netconsul.R;
import com.gravata.netconsul.adapter.GenericArrayAdapter;
import com.gravata.netconsul.model.SimNao;

import java.util.Arrays;

/**
 * Created by bruno on 01/11/15.
 */
public class SimNaoComboAdapter extends GenericArrayAdapter<SimNao> {

    public SimNaoComboAdapter(Context context) {

        super(context, R.layout.spinner_item, android.R.layout.simple_spinner_dropdown_item, Arrays.asList(SimNao.values()), null);
    }

    @Override
    public void drawText(TextView textView, SimNao object) {
        textView.setText(object.getTexto());
    }

}
