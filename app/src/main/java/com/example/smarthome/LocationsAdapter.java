package com.example.smarthome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class LocationsAdapter extends ArrayAdapter {

    private Context context;

    private int idLayout;

    private List<String[]> misDatos;

    public LocationsAdapter(@NonNull Context context, int resource, @NonNull List<String[]> objects) {
        super(context, resource, objects);

        this.context = context;
        this.idLayout = resource;
        this.misDatos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LinearLayout vista;
        if(null != convertView){
            vista = (LinearLayout) convertView;
        } else {
            LayoutInflater inflador =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = (LinearLayout) inflador.inflate(idLayout, parent, false);
        }
        TextView tvLinea1 = vista.findViewById(R.id.tvItem1);
        float val = new Float(misDatos.get(position)[1]);
        if( val < 2.00){
            vista.setBackgroundColor(context.getResources().getColor(R.color.colorBajo));
        } else if(val < 5.00){
            vista.setBackgroundColor(context.getResources().getColor(R.color.colorModerado));
        } else if(val < 7.00){
            vista.setBackgroundColor(context.getResources().getColor(R.color.colorAlto));
        } else if(val < 10.00){
            vista.setBackgroundColor(context.getResources().getColor(R.color.colorMuyAlto));
        } else {
            vista.setBackgroundColor(context.getResources().getColor(R.color.colorExtremo));
        }

        tvLinea1.setText(misDatos.get(position)[0]);

        TextView tvLinea2 = vista.findViewById(R.id.tvItem2);
        tvLinea2.setText(misDatos.get(position)[1]);

        return vista;
    }
}
