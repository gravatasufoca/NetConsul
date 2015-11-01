package com.gravata.netconsul.adapter;

import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gravata.netconsul.model.EntidadeAbstrata;


public abstract class GenericArrayAdapter<T> extends ArrayAdapter<T> {

    // Vars
    private LayoutInflater mInflater;
    private int SPINNER_LAYOUT = 0;
    private int TEXT_LAYOUT = 0;
    private String NO_SELECTED;
    private List<T> objects;
    private Context context;

    public GenericArrayAdapter(Context context, int spinner_resource, int text_resource, List<T> objects, String no_selected) {
        super(context, spinner_resource, text_resource, objects);
        this.NO_SELECTED = no_selected;
        this.objects = objects;
        preparePrompt();
        this.SPINNER_LAYOUT = spinner_resource;
        this.TEXT_LAYOUT = text_resource;
        this.context = context;
        init(context);
    }

    private void preparePrompt() {
        if (NO_SELECTED != null && !NO_SELECTED.isEmpty()) {

            objects.add(0, (T) new EntidadeAbstrata() {
                @Override
                public Serializable getId() {
                    return null;
                }
            });
        }
    }

    public void setData(List<T> objects) {
        this.objects.clear();

        for (T t : objects) {
            this.objects.add(t);
        }

        preparePrompt();
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return objects;
    }

    // Headers
    public abstract void drawText(TextView textView, T object);

    private void init(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            convertView = mInflater.inflate(
                    SPINNER_LAYOUT, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        T item = getItem(position);

        if(item.getClass().isEnum())
            drawText(vh.textView, item);
        else if (item.getClass().isAssignableFrom(String.class) || ((EntidadeAbstrata) item).getId() != null)
            drawText(vh.textView, item);
        else {
            vh.textView.setText(NO_SELECTED);
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        final ViewHolder vh;
        if (convertView == null) {
            convertView = mInflater.inflate(
                    TEXT_LAYOUT, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        T item = getItem(position);
        if(item.getClass().isEnum())
            drawText(vh.textView, item);
        else if (item.getClass().isAssignableFrom(String.class) || ((EntidadeAbstrata) item).getId() != null)
            drawText(vh.textView, item);
        else {
            vh.textView.setText(NO_SELECTED);
        }

        return convertView;
    }


    static class ViewHolder {

        TextView textView;

        private ViewHolder(View rootView) {
            textView = (TextView) rootView.findViewById(android.R.id.text1);
        }
    }
}