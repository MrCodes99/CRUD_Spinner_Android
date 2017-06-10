package com.michell.demo08;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MICHELL on 10/06/2017.
 */

public class SPMainAdapter extends ArrayAdapter<Persona> {
    public SPMainAdapter(Context context) {
        super(context, 0, new ArrayList<Persona>());
    }

    // SPINNER SIN DESPLEGAR
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);

        TextView tvFullName;

        tvFullName = (TextView) convertView.findViewById(R.id.tvFullName);

        Persona persona = getItem(position);

        tvFullName.setText(persona.getNombre() + " " + persona.getApellido());

        return convertView;
    }

    // SPINNER DESPLEGADO
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item_dropdownview, parent, false);

        TextView tvFullName;

        tvFullName = (TextView) convertView.findViewById(R.id.tvDropdownviewFullName);

        Persona persona = getItem(position);

        tvFullName.setText(persona.getNombre() + " " + persona.getApellido());

        return convertView;
    }
}
