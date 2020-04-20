package com.example.mobiilisovellus;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Lisaa_Tehtava extends AppCompatActivity implements View.OnClickListener  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisaa_tehtava);
        findViewById(R.id.saveTask).setOnClickListener(this);
        findViewById(R.id.backButton).setOnClickListener(this);



    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {                        //Lähettää tehtävän nimen eteenpäin
        if (view.getId() == R.id.saveTask) {

            Intent resultIntent = new Intent();

            try{
                EditText aika = findViewById(R.id.taskDuration);
                int tehtavanKesto = Integer.parseInt(aika.getText().toString());

                EditText tehtavanKuvaus = findViewById(R.id.taskDescription);
                String kuvaus = tehtavanKuvaus.getText().toString();

                TextView tehtavaNimi = findViewById(R.id.addTaskname);
                String nimi = tehtavaNimi.getText().toString();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


                Tehtava t = new Tehtava(nimi,kuvaus,LocalDateTime.now().plusDays(tehtavanKesto).format(formatter),0);
                resultIntent.putExtra("LisattyTehtava", t);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();


            }catch (Exception e){
                e.printStackTrace();

                //tähän vaikka dialogi epäonnistumiselle
            }



        }
        if(view.getId() == R.id.backButton) {
            startActivity(new Intent(Lisaa_Tehtava.this, Tehtava_Esikatselu.class));
        }
    }


}