package com.example.zaky.kasircantik;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class TambahMenu extends AppCompatActivity {


    private final String MENU_BASE_URL = "http://proyekdev.ga/api/menu";
    private EditText editNama;
    private EditText editKategori;
    private EditText editHarga;
    private Button simpan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        editNama        = (EditText)findViewById(R.id.editNama);
        editKategori    = (EditText)findViewById(R.id.editKategori);
        editHarga       = (EditText)findViewById(R.id.editHarga);

        simpan = (Button)findViewById(R.id.btnSimpan);

        simpan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                new PostMenu().execute();

                finish();

            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id == android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    public class PostMenu extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground (String... params){
            final String nama       = editNama.getText().toString();
            final String kategori   = editKategori.getText().toString();
            final String harga      = editHarga.getText().toString();

            try {

                URL url = new URL(MENU_BASE_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.connect();


                JSONObject jsonParam = new JSONObject();
                jsonParam.put("nama", nama);
                jsonParam.put("kategori", kategori);
                jsonParam.put("harga", harga);

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
