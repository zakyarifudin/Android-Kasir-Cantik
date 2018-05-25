package com.example.zaky.kasircantik;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Loginpage extends AppCompatActivity {
    EditText username,password;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        username = (EditText)findViewById(R.id.edit_username);
        password = (EditText)findViewById(R.id.edit_password);

    }

    public void loginPage(View view) {
        /*Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
        */

        new LoginTo().execute();

    }

    public class LoginTo extends AsyncTask<String,Void,String> {
        Context cnt;
        String user = username.getText().toString();
        String pass = password.getText().toString();
        @Override
        protected String doInBackground(String... strings) {
            URL url=null;
            String result=null;
            String MENU_BASE_URL = "http://proyekdev.ga/login?user="+user+"&pass="+pass;

                try {

                    url= new URL(MENU_BASE_URL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    int response = connection.getResponseCode();
                    Log.d("debug1", "response code :" + response);

                    BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    StringBuilder total = new StringBuilder();
                    String line;
                    while((line=r.readLine()) != null){
                        total.append(line);
                    }

                    result=total.toString();
                    Log.d("debug1","Output" + result);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONObject login = jsonObject.getJSONObject("data");
                Log.d("debug1","Status :" + login.getInt("login"));


                if(login.getInt("login")==1) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Anda salah memasukkan username password",Toast.LENGTH_LONG).show();
                    username.setText("");
                    password.setText("");
                }

            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }

    }
}
