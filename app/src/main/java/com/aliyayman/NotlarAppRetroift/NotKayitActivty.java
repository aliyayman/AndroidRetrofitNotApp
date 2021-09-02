package com.aliyayman.NotlarAppRetroift;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotKayitActivty extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText editTextDers,editTextNot1,editTextNot2;
    private Button buttonKaydet;
    private NotlarDaoInterface notlarDaoInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_kayit_activty);

        editTextDers=findViewById(R.id.editTextDers);
        editTextNot1=findViewById(R.id.editTextNot1);
        editTextNot2=findViewById(R.id.editTextNot2);
        buttonKaydet=findViewById(R.id.buttonKaydet);
        toolbar=findViewById(R.id.toolbar);


        toolbar.setTitle("Not Kayıt");
        setSupportActionBar(toolbar);

        notlarDaoInterface=ApiUtils.getNotlarDaoInterface();

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ders_adi=editTextDers.getText().toString().trim();
                String not1=editTextNot1.getText().toString().trim();
                String not2=editTextNot2.getText().toString().trim();



                if(TextUtils.isEmpty(ders_adi)){
                    Snackbar.make(v,"Ders adi giriniz...",Snackbar.LENGTH_SHORT).show();

                    return;
                }
                if(TextUtils.isEmpty(not1)){
                    Snackbar.make(v,"1. Notunuzu giriniz...",Snackbar.LENGTH_SHORT).show();

                    return;
                }
                if(TextUtils.isEmpty(not2)){
                    Snackbar.make(v,"2. Notunuzu giriniz..",Snackbar.LENGTH_SHORT).show();

                    return;
                }

               notlarDaoInterface.notAdd(ders_adi,Integer.parseInt(not1),Integer.parseInt(not2)).enqueue(new Callback<CRUDCevap>() {
                   @Override
                   public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

                   }

                   @Override
                   public void onFailure(Call<CRUDCevap> call, Throwable t) {

                   }
               });


                startActivity(new Intent(NotKayitActivty.this,MainActivity.class));

                finish();
            }
        });

    }
}