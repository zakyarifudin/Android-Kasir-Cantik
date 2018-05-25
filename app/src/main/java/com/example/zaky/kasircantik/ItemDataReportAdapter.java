package com.example.zaky.kasircantik;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zaky on 05/20/2018.
 */

public class ItemDataReportAdapter extends ArrayAdapter<ItemDataReport> {

    private Context context;
    private ArrayList<ItemDataReport> values;


    public ItemDataReportAdapter(Context context, ArrayList<ItemDataReport> values){
        super(context,R.layout.list_item_report, values);
        this.values = values;
        this.context =context;

    }


    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_report, parent, false);

        //TextView idOrderText    = (TextView)view.findViewById(R.id.text1);
        TextView pemesanText    = (TextView)view.findViewById(R.id.text2);
        TextView totalText      = (TextView)view.findViewById(R.id.text3);
        //TextView tanggalText    = (TextView)view.findViewById(R.id.text4);


        pemesanText.setText(values.get(position).pengunjung);
        totalText.setText("Rp." + values.get(position).pendapatan);
        //tanggalText.setText(values.get(position).itemTanggal);

        return view;
    }
}
