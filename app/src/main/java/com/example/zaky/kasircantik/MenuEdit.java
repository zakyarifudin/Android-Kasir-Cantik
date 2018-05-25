package com.example.zaky.kasircantik;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MenuEdit extends AppCompatActivity {

    private ListView listView;
    private ArrayList<ItemData> values;
    private ItemAdapter adapter;

    private final String MENU_BASE_URL = "http://proyekdev.ga/api/menu/";
    private String idMenu;

    private EditText editNama;
    private EditText editKategori;
    private EditText editHarga;
    private Button simpan;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        Intent intent=getIntent();

        if(intent.hasExtra("idMenu")){
            idMenu =intent.getStringExtra("idMenu");

        }

        editNama        = (EditText)findViewById(R.id.editNama);
        editKategori    = (EditText)findViewById(R.id.editKategori);
        editHarga       = (EditText)findViewById(R.id.editHarga);

        simpan = (Button)findViewById(R.id.btnSimpan);

        simpan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                new UpdateMenu().execute();

                finish();

            }
        });

        new ShowMenu().execute(MENU_BASE_URL+idMenu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id == android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    public class ShowMenu extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            URL url=null;
            String result=null;

            try{
                url= new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int response = connection.getResponseCode();
                Log.d("debug3", "response code :" + response);
                Log.d("debug3", strings[0]);

                BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder total = new StringBuilder();
                String line;
                while((line=r.readLine()) != null){
                    total.append(line);
                }

                result=total.toString();
                Log.d("debug3","Output" + result);

            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }



        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONObject menu = jsonObject.getJSONObject("data");
                editNama.setText(menu.getString("nama"), TextView.BufferType.SPANNABLE);
                editKategori.setText(menu.getString("kategori"), TextView.BufferType.SPANNABLE);
                editHarga.setText(String.valueOf(menu.getInt("harga")), TextView.BufferType.SPANNABLE);
                // item.itemImage      = volumeInfo.getJSONObject("imageLinks").getString("smallThumbnail");


            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
    }


    public class UpdateMenu extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground (String... params){
            final String nama       = editNama.getText().toString();
            final String kategori   = editKategori.getText().toString();
            final String harga      = editHarga.getText().toString();

            try {

                URL url = new URL(MENU_BASE_URL+idMenu);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("PUT");
                conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.connect();


                JSONObject jsonParam = new JSONObject();

                if(nama!=""){
                    jsonParam.put("nama", nama);
                }
                if(kategori!=""){
                    jsonParam.put("kategori", kategori);
                }
                if(harga!=""){
                    jsonParam.put("harga", harga);
                }


                Log.i("debug1", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());

                os.flush();
                os.close();

                Log.i("debug1", String.valueOf(conn.getResponseCode()));
                Log.i("debug1" , conn.getResponseMessage());

                conn.disconnect();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


    }
}
