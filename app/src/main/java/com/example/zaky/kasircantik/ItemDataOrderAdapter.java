package com.example.zaky.kasircantik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by zaky on 05/11/2018.
 */

public class ItemDataOrderAdapter extends ArrayAdapter<ItemDataOrder>{
    private Context context;
    private ArrayList<ItemDataOrder> values;


    public ItemDataOrderAdapter(Context context, ArrayList<ItemDataOrder> values){
        super(context,R.layout.list_item_order, values);
        this.values = values;
        this.context =context;

    }


    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_order, parent, false);

        TextView idOrderText    = (TextView)view.findViewById(R.id.text1);
        TextView pemesanText    = (TextView)view.findViewById(R.id.text2);
        TextView totalText      = (TextView)view.findViewById(R.id.text3);
        TextView tanggalText    = (TextView)view.findViewById(R.id.text4);

        idOrderText.setText(values.get(position).itemIdOrder);
        pemesanText.setText(values.get(position).itemPemesan);
        totalText.setText("Rp." + values.get(position).itemTotal);
        tanggalText.setText(values.get(position).itemTanggal);

        return view;
    }
}

