package com.example.zaky.kasircantik;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    //Main nya
    private ListView listView;
    private ArrayList<ItemData> values;
    private ItemAdapter adapter;

    private RecyclerView recyclerView;
    private RecItemAdapter radapter;
    private final String MENU_BASE_URL = "http://proyekdev.ga/api/menu";

    private Button addMenu;
    Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public void onResume() {
        super.onResume();

        new GetMenu().execute();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        recyclerView = (RecyclerView)v.findViewById(R.id.recycleView);

        values = new ArrayList<>();



        addMenu = (Button)v.findViewById(R.id.tambahMenu);

        addMenu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), TambahMenu.class);
                startActivity(intent);
            }
        });

        return v;


    }

    public class GetMenu extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            URL url=null;
            String result=null;

            try{
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
                JSONArray itemArray = jsonObject.getJSONArray("data");
                values = new ArrayList<>();

                for(int i=itemArray.length()-1;i>=0;i--){
                    JSONObject menu         = itemArray.getJSONObject(i);
                    ItemData item       = new ItemData();
                    item.itemIdMenu     = menu.getInt("id");
                    item.itemTitle      = menu.getString("nama");
                    item.itemCategory   = menu.getString("kategori");
                    item.itemPrice      = menu.getInt("harga");
                   // item.itemImage      = volumeInfo.getJSONObject("imageLinks").getString("smallThumbnail");
                    values.add(item);

                }


                radapter = new RecItemAdapter(getContext(),values);
                recyclerView.setAdapter(radapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

}
