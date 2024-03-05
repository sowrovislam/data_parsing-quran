package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Format;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =findViewById(R.id.tex);
     progressBar =findViewById(R.id.pr);


        String url ="https://cdn.jsdelivr.net/npm/quran-json@3.1.2/dist/quran_bn.json";

progressBar.setVisibility(View.VISIBLE);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                progressBar.setVisibility(View.GONE);






                for (int x=0;x<response.length();x++)

                    try {
                        JSONObject jsonObject=response.getJSONObject(x);



                         String name=jsonObject.getString("name");


                 String transliteration=jsonObject.getString("transliteration");

                 textView.append(transliteration+"\n\n\n"+name);







//                        JSONArray jsonArray=jsonObject.getJSONArray("verses");
//
//
//
//                        JSONObject jsonObject1=jsonArray.getJSONObject(0);
//
//                        String name =jsonObject1.getString("translation");
//
//
//                        textView.append(""+name);
//
//





                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                textView.setError("err");

            }
        });


        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonArrayRequest);


    }
}