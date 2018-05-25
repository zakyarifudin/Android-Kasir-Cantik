package com.example.zaky.kasircantik;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by zaky on 05/01/2018.
 */

public class RecItemAdapter extends RecyclerView.Adapter<RecItemAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ItemData> values;
    private final LayoutInflater mInflater;
    private final String MENU_BASE_URL = "http://proyekdev.ga/api/menu/";


    public RecItemAdapter(Context context, ArrayList<ItemData> values){
        this.context = context;
        this.values  = values;
        this.mInflater = LayoutInflater.from(context);

    }


    @Override
    public RecItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final RecItemAdapter.ViewHolder holder, final int position) {

        final String idMenu = String.valueOf(values.get(position).itemIdMenu);

        holder.titleText.setText(values.get(position).itemTitle);
        holder.categoryText.setText(values.get(position).itemCategory);
        holder.priceText.setText("Rp." + values.get(position).itemPrice);

        // holder.imageView.setImageResource(R.mipmap.ic_launcher);

        //new LoadImage(holder.imageView).execute(values.get(position).itemImage);



        holder.btnHapus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new DeleteMenu().execute(MENU_BASE_URL+idMenu);
                Intent intent=new Intent(context, MainActivity.class);
                context.startActivity(intent);


            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MenuEdit.class);
                intent.putExtra("idMenu", idMenu);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleText;
        TextView priceText;
        TextView categoryText;
        ImageView imageView;
        TextView idMenuText;
        Button btnEdit;
        Button btnHapus;

        public ViewHolder(View view){
            super(view);
            titleText    = (TextView)view.findViewById(R.id.text1);
            categoryText = (TextView)view.findViewById(R.id.text2);
            priceText    = (TextView)view.findViewById(R.id.text3);
        //    imageView    = (ImageView)view.findViewById(R.id.image1);
            btnEdit      = (Button)view.findViewById(R.id.btnEdit);
            btnHapus     = (Button)view.findViewById(R.id.btnHapus);
            idMenuText   = (TextView)view.findViewById(R.id.text4);
        }

    }

   /* public class LoadImage extends AsyncTask<String, Integer, Bitmap> {

        private ImageView imageView;

        public LoadImage(ImageView imageView){
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url=null;
            Bitmap bitmap= null;

            try {
                url = new URL(strings[0]);
                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }


            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }*/

    public class DeleteMenu extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            URL url=null;
            String result=null;

            try{
                url= new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("DELETE");
                connection.connect();
                int response = connection.getResponseCode();
                Log.d("debug2", "response code :" + response);
                Log.d("debug2", strings[0]);

            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
    }

}

