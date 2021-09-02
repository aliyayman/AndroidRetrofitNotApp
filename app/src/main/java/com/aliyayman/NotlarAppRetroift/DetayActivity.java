package com.aliyayman.NotlarAppRetroift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetayActivity extends AppCompatActivity {
    private EditText editTextDers,editTextNot1,editTextNot2;
    private Toolbar toolbar;
    private Notlar not;
    private NotlarDaoInterface notlarDaoInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);
        editTextDers=findViewById(R.id.editTextDers);
        editTextNot1=findViewById(R.id.editTextNot1);
        editTextNot2=findViewById(R.id.editTextNot2);
        toolbar=findViewById(R.id.toolbar);

        not= (Notlar) getIntent().getSerializableExtra("nesne");


        editTextDers.setText(not.getDersAdi());
        editTextNot1.setText(String.valueOf(not.getNot1()));
        editTextNot2.setText(String.valueOf(not.getNot2()));

        notlarDaoInterface=ApiUtils.getNotlarDaoInterface();

        toolbar.setTitle("Not Detay");
        setSupportActionBar(toolbar);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_sil:
                Snackbar.make(toolbar,"Silinsin mi?",Snackbar.LENGTH_SHORT).setAction("Evet", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                       notlarDaoInterface.notDelete(Integer.parseInt(not.getNotId())).enqueue(new Callback<CRUDCevap>() {
                           @Override
                           public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {


                           }

                           @Override
                           public void onFailure(Call<CRUDCevap> call, Throwable t) {

                           }
                       });
                        startActivity(new Intent(DetayActivity.this,MainActivity.class));

                        finish();

                    }
                }).show();
                return true;
            case R.id.action_duzenle:
                String ders_adi=editTextDers.getText().toString().trim();
                String not1=editTextNot1.getText().toString().trim();
                String not2=editTextNot2.getText().toString().trim();

                if(TextUtils.isEmpty(ders_adi)){
                    Snackbar.make(toolbar,"Ders adi giriniz...",Snackbar.LENGTH_SHORT).show();

                    return false;
                }
                if(TextUtils.isEmpty(not1)){
                    Snackbar.make(toolbar,"1. Notunuzu giriniz...",Snackbar.LENGTH_SHORT).show();

                    return false;
                }
                if(TextUtils.isEmpty(not2)){
                    Snackbar.make(toolbar,"2. Notunuzu giriniz..",Snackbar.LENGTH_SHORT).show();

                    return false;
                }

                notlarDaoInterface.notUpdate(Integer.parseInt(not.getNotId()),ders_adi,Integer.parseInt(not1),Integer.parseInt(not2)).enqueue(new Callback<CRUDCevap>() {
                    @Override
                    public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

                    }

                    @Override
                    public void onFailure(Call<CRUDCevap> call, Throwable t) {

                    }
                });
                startActivity(new Intent(DetayActivity.this,MainActivity.class));

                finish();

                return true;

            default:
                return false;
        }
    }
}