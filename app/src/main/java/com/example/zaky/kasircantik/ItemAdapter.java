package com.example.zaky.kasircantik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zaky on 05/01/2018.
 */

public class ItemAdapter extends ArrayAdapter<ItemData> {

    private Context context;
    private ArrayList<ItemData> values;


    public ItemAdapter(Context context, ArrayList<ItemData> values){
        super(context,R.layout.list_item, values);
        this.values = values;
        this.context =context;

    }


    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item, parent, false);

        TextView titleText      = (TextView)view.findViewById(R.id.text1);
        TextView categoryText   = (TextView)view.findViewById(R.id.text2);
        TextView priceText      = (TextView)view.findViewById(R.id.text3);
        //ImageView imageView     = (ImageView)view.findViewById(R.id.image1);
        TextView idMenuText     = (TextView)view.findViewById(R.id.text4);

        titleText.setText(values.get(position).itemTitle);
        categoryText.setText(values.get(position).itemCategory);
        priceText.setText("Rp." + values.get(position).itemPrice);
       // imageView.setImageResource(R.mipmap.ic_launcher);

        return view;
    }

}

