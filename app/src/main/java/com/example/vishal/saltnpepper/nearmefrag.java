package com.example.vishal.saltnpepper;


import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class nearmefrag extends Fragment {

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public List<itemrest> listitem;
    databasehandler mydb;

    public nearmefrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        String strt=getArguments().getString("location");
        Toast.makeText(getContext(),strt,Toast.LENGTH_SHORT).show();
        mydb=new databasehandler(getActivity());
        View vv= inflater.inflate(R.layout.fragment_nearmefrag, container, false);
        recyclerView=(RecyclerView)vv.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        Cursor res=mydb.visc(strt);
//        Toast.makeText(getActivity(),res.getCount(),Toast.LENGTH_SHORT).show();
        if(res.getCount()==0)
        {
            Toast.makeText(getActivity(),"Currently not available at given location try some other location :)",Toast.LENGTH_SHORT).show();
        }

        else {
            listitem=new ArrayList<>();
             //Toast.makeText(getActivity(),"In else",Toast.LENGTH_SHORT).show();
           StringBuffer buffer=new StringBuffer();
            while(res.moveToNext())
            {
                buffer.append(res.getString(0)+res.getString(1)+res.getString(2)+res.getString(3)+"\n");
                itemrest irr = new itemrest(res.getString(1) , res.getString(5), "30% offer on orders", res.getString(7)+"stars   Rs"+res.getString(6));
                Log.i("Hello",irr+"");
                listitem.add(irr);
                adapter=new adapter(listitem,getActivity());
                recyclerView.setAdapter(adapter);

            }


        }


        return vv;
       //vineet BackgroundTask backgroundTask=new BackgroundTask();
       //vineet backgroundTask.execute(strt);
       //vineet return vv;

    }
    class BackgroundTask extends AsyncTask<String,String,String>{
        String res_url="";
        String str="";
        @Override
        protected void onPreExecute() {
            res_url="https://grooviest-recruiter.000webhostapp.com/json_restaurants.php";
        }

        @Override
        protected String doInBackground(String... args) {
            str=args[0];
            try {
                URL url=new URL(res_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);
                int response_code = httpURLConnection.getResponseCode();
                if(response_code == HttpURLConnection.HTTP_OK){
                    InputStream input = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return (result.toString());


                }
                else {
                    return ("Unsuccessful");

                }



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

          //  Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
            parseJSON(result,str);



        }
        public void parseJSON(String result,String str){
           // Toast.makeText(getActivity(),"In parse JSON",Toast.LENGTH_SHORT).show();
            listitem=new ArrayList<>();
            try {
             //   Toast.makeText(getActivity(),"In try block",Toast.LENGTH_SHORT).show();
                JSONObject jsonObject=new JSONObject(result);
                JSONArray jArray =jsonObject.getJSONArray("restaurants");
                Toast.makeText(getActivity(),str,Toast.LENGTH_LONG).show();
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    DataFish fishData = new DataFish();
                    fishData.Name= json_data.getString("name");
                    fishData.Type= json_data.getString("type");
                    fishData.address= json_data.getString("address");
                    fishData.ratings= json_data.getInt("rating");
                    fishData.price= json_data.getInt("price");
                    if(fishData.address.equals(str)){
                        Toast.makeText(getActivity(),fishData.Name,Toast.LENGTH_SHORT).show();
                        itemrest irr=new itemrest(fishData.Name,fishData.Type,"30% Discount on Orders",fishData.ratings+"starts  Price for 2 Rs"+fishData.price);
                        listitem.add(irr);
                        adapter=new adapter(listitem,getActivity());
                        recyclerView.setAdapter(adapter);
                    }





                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
