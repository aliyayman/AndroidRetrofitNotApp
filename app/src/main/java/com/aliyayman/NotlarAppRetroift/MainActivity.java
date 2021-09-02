package com.aliyayman.NotlarAppRetroift;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView rv;
    private FloatingActionButton fab;
    private ArrayList<Notlar> notlarList;
    private NotlarAdapter adapter;
    private NotlarDaoInterface notlarDaoInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.rv);
        toolbar=findViewById(R.id.toolbar);
        fab=findViewById(R.id.fab);


        toolbar.setTitle("Not uygulaması");
        setSupportActionBar(toolbar);

        notlarDaoInterface=ApiUtils.getNotlarDaoInterface();




        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        tumNotlar();



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,NotKayitActivty.class));
            }
        });
    }

    public void tumNotlar(){
        notlarDaoInterface.tumNotlar().enqueue(new Callback<NotlarCevap>() {
            @Override
            public void onResponse(Call<NotlarCevap> call, Response<NotlarCevap> response) {

                double toplam=0;
                double ortalama=0;

                List<Notlar> notlar=response.body().getNotlar();
                for(Notlar n:notlar){
                    toplam=toplam+(Integer.parseInt(n.getNot1())+Integer.parseInt(n.getNot2()))/2;
                    ortalama=toplam/notlar.size();

                    toolbar.setSubtitle("ortalama:"+ortalama);
                }

                adapter=new NotlarAdapter(MainActivity.this,notlar);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<NotlarCevap> call, Throwable t) {

            }
        });
    }



}