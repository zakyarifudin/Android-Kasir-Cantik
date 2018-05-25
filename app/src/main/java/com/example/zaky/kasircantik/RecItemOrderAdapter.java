package com.example.zaky.kasircantik;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zaky on 05/11/2018.
 */

public class RecItemOrderAdapter extends RecyclerView.Adapter<RecItemOrderAdapter.ViewHolder>{
    private Context context;
    private ArrayList<ItemDataOrder> values;
    private final LayoutInflater mInflater;
    private final String MENU_BASE_URL = "http://proyekdev.ga/api/menu/";


    public RecItemOrderAdapter(Context context, ArrayList<ItemDataOrder> values){
        this.context = context;
        this.values  = values;
        this.mInflater = LayoutInflater.from(context);

    }


    @Override
    public RecItemOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_order, parent, false);

        return new ViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final RecItemOrderAdapter.ViewHolder holder, final int position) {

        final String idMenu = String.valueOf(values.get(position).itemIdOrder);

        holder.idOrderText.setText(idMenu);
        holder.pemesanText.setText(values.get(position).itemPemesan+" ");
        holder.totalText.setText("Rp." + String.valueOf(values.get(position).itemTotal)+" ");
        holder.tanggalText.setText(values.get(position).itemTanggal+" ");

        // holder.imageView.setImageResource(R.mipmap.ic_launcher);

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView pemesanText;
        TextView totalText;
        TextView tanggalText;
        TextView idOrderText;


        public ViewHolder(View view){
            super(view);
            idOrderText     = (TextView)view.findViewById(R.id.text1);
            pemesanText     = (TextView)view.findViewById(R.id.text2);
            totalText       = (TextView)view.findViewById(R.id.text3);
            tanggalText     = (TextView)view.findViewById(R.id.text4);
        }

    }
}
