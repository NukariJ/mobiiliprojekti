package com.example.mobiilisovellus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
import android.widget.ListView;
=======
import android.widget.ArrayAdapter;
import android.widget.EditText;
>>>>>>> master
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Tehtava_Esikatselu extends AppCompatActivity {

    TextView taskName;
    TextView taskInfo;
    TextView taskDate;

<<<<<<< HEAD
    private static final int MY_REQUEST_CODE = 1;

    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<>();
    ListView listView;
    TextView missionHeadline;
    //Button statebtn;
    //String str;
=======
>>>>>>> master
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tehtava_esikatselu);
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String info = intent.getStringExtra("DESCRIPTION");
         //str = getIntent().getStringExtra("msg");
        String date = intent.getStringExtra("DATE");
        findViewById(R.id.addSubtask).setOnClickListener(buttonClickListener);
        findViewById(R.id.returnButton).setOnClickListener(buttonClickListener);
        findViewById(R.id.editButton).setOnClickListener(buttonClickListener);
        taskName = findViewById(R.id.taskName);
        taskInfo = findViewById(R.id.taskDescription);
        taskDate = findViewById(R.id.timeSet);
        taskName.setText(name);
        taskInfo.setText(info);
        taskDate.setText(date);

}



public View.OnClickListener buttonClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.returnButton:
                startActivity(new Intent(Tehtava_Esikatselu.this, MainActivity.class));
                break;
            case R.id.editButton:
                startActivity(new Intent(Tehtava_Esikatselu.this, Lisaa_Tehtava.class));
                break;
            case R.id.addSubtask:
                Intent intent = new Intent(Tehtava_Esikatselu.this, Lisaa_Alitehtava.class);
                startActivityForResult(intent, MY_REQUEST_CODE);
                break;

        }

    }
};

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                String subtaskname = data.getStringExtra("keyName");

                list.add(subtaskname);
                adapter.notifyDataSetChanged();
            }
        }
    }

   /*public void buttonState()            //Määrittää suoritspainikkeen värin ja tekstin
   {
       Button btn = findViewById(R.id.setStateButton);
           btn.setBackgroundColor(Color.GREEN);
           btn.setText("Valmis");
           if(btn = btn.setBackgroundColor(Color.GREEN))

   }*/


}

