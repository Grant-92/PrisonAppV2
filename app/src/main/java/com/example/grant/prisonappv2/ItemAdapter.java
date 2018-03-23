package com.example.grant.prisonappv2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Grant on 22/03/2018.
 */

public class ItemAdapter extends BaseAdapter {


    //TODO write the prisoner class and include in the Arraylist + finish this class
    private  ArrayList<Prisoner> List = new ArrayList<>();
    Context context;


    public ItemAdapter(Context context, ArrayList<Prisoner> list ){

        this.context = context;
        this.List = list;
    }
    @Override
    public int getCount() {
        return List.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
