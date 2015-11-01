package com.gravata.netconsul.adapter.combos;

import android.content.Context;
import android.widget.TextView;

import com.gravata.netconsul.R;
import com.gravata.netconsul.adapter.GenericArrayAdapter;
import com.gravata.netconsul.model.Cliente;

import java.util.List;

/**
 * Created by bruno on 01/11/15.
 */
public class ClienteComboAdapter extends GenericArrayAdapter<Cliente> {

    public ClienteComboAdapter(Context context, List<Cliente> objects) {
        super(context, R.layout.spinner_item, android.R.layout.simple_spinner_dropdown_item, objects, null);
    }

    @Override
    public void drawText(TextView textView, Cliente object) {
        textView.setText(object.getNomeFantasia());
    }
}
