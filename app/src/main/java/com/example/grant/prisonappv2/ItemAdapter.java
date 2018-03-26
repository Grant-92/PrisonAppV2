package com.example.grant.prisonappv2;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grant on 22/03/2018.
 */

public class ItemAdapter extends ArrayAdapter<Prisoner> {

    private Activity theContext;
    private List<Prisoner> inmates;



    public ItemAdapter(Activity theContext, List<Prisoner> inmates){
        super(theContext, R.layout.custom_listview_layout, inmates);
        this.theContext = theContext;
        this.inmates = inmates;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = theContext.getLayoutInflater();

        View listedItem = inflater.inflate(R.layout.custom_listview_layout, null, true);



        TextView id = listedItem.findViewById(R.id.idTV);
        TextView name = listedItem.findViewById(R.id.nameTV);
        TextView charge = listedItem.findViewById(R.id.chargeTV);
        TextView sentence = listedItem.findViewById(R.id.sentenceTV);

        Prisoner prisoner = inmates.get(position);

        id.setText(prisoner.getId());
        name.setText(prisoner.getName());
        charge.setText(prisoner.getCharge());
        sentence.setText(String.valueOf(prisoner.getSentence()));

        return  listedItem;

    }
}