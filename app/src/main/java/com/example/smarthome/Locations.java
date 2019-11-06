package com.example.smarthome;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Locations {

    Map<String, String[]> data;

    Context context;

    public Locations(Context context){
        this.context = context;
        this.data = new HashMap<>();
        this.addMadrid();
        this.addCanarias();
        this.addCataluña();
        this.addPaisVasco();
        this.addAndalucia();
    }

    public void addMadrid(){
        String[] Madrid = this.context.getResources().getStringArray(R.array.Madrid);
        this.data.put("Madrid", Madrid);
    }

    public void addCanarias(){
        String[] Canarias = this.context.getResources().getStringArray(R.array.Canarias);
        this.data.put("Canarias", Canarias);
    }

    public void addCataluña(){
        String[] Cataluña = this.context.getResources().getStringArray(R.array.Cataluña);
        this.data.put("Cataluña", Cataluña);
    }
    public void addPaisVasco(){
        String[] Pais_Vasco = this.context.getResources().getStringArray(R.array.Pais_Vasco);
        this.data.put("Pais_Vasco",Pais_Vasco);
    }

    public void addAndalucia(){
        String[] Andalucia = this.context.getResources().getStringArray(R.array.Andalucía);
        this.data.put("Andalucía", Andalucia);
    }

    public ArrayList<String[]> getData(){
        ArrayList<String[]> listData = new ArrayList<String[]>();
        for(String[] array : this.data.values()){
            listData.add(array);
        }
        return listData;
    }

}
