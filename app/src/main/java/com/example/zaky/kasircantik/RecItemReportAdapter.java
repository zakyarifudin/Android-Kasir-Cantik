package com.example.zaky.kasircantik;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zaky on 05/20/2018.
 */

public class RecItemReportAdapter extends RecyclerView.Adapter<RecItemReportAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ItemDataReport> values;
    private final LayoutInflater mInflater;
    private final String MENU_BASE_URL = "http://proyekdev.ga/api/order/";


    public RecItemReportAdapter(Context context, ArrayList<ItemDataReport> values){
        this.context = context;
        this.values  = values;
        this.mInflater = LayoutInflater.from(context);

    }


    @Override
    public RecItemReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_report, parent, false);

        return new RecItemReportAdapter.ViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final RecItemReportAdapter.ViewHolder holder, final int position) {

        holder.pemesanText.setText(String.valueOf(values.get(position).pengunjung));
        holder.totalText.setText("Rp." + String.valueOf(values.get(position).pendapatan));

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView pemesanText;
        TextView totalText;



        public ViewHolder(View view){
            super(view);
            pemesanText     = (TextView)view.findViewById(R.id.text2);
            totalText       = (TextView)view.findViewById(R.id.text3);
        }

    }
}
