package com.example.mobiilisovellus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
import android.widget.TextView;

public class Alitehtava_Esikatselu extends AppCompatActivity implements View.OnClickListener {

    TextView Subtaskname;
    TextView SubtaskDescription;
    String name;
    String info;
    Alitehtava subTask;

    private static final int MY_REQUEST_CODE = 1;


    Button button2;
    boolean buttonStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alitehtava_esikatselu);

        Intent intent = getIntent();

        subTask = (Alitehtava) intent.getSerializableExtra("MOVEDSUBTASK");

        name = subTask.getAlitehtavannimi();
        info = subTask.getAlitehtavankuvaus();
        buttonStatus = subTask.isSuoritettu();

        setButtonStatus();

        Subtaskname = findViewById(R.id.SubtaskName);
        SubtaskDescription = findViewById(R.id.SubtaskDescription);

        Subtaskname.setText(name);
        SubtaskDescription.setText(info);

        findViewById(R.id.subTaskPercent).setOnClickListener(this);
        findViewById(R.id.returnButton).setOnClickListener(this);
        findViewById(R.id.deleteTask).setOnClickListener(this);
        findViewById(R.id.SubtaskEditor).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.subTaskPercent) {
            buttonState();
        }
        else if (v.getId() == R.id.returnButton){

            Intent i = new Intent();

            subTask.setSuoritettu(buttonStatus);

            i.putExtra("R_SUBTASK",subTask);


            setResult(Activity.RESULT_OK,i);
            finish();
        }
        else if(v.getId() == R.id.deleteTask){

            sendDataBackToTasks(2);
        }
        else if (v.getId() == R.id.SubtaskEditor){
            Intent intent2 = new Intent(Alitehtava_Esikatselu.this, Muokkaa_Alitehtava.class);
            intent2.putExtra("name", subTask.getAlitehtavannimi());
            intent2.putExtra("kuvaus", subTask.getAlitehtavankuvaus());
            startActivityForResult(intent2, MY_REQUEST_CODE);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            subTask.setAlitehtavannimi(data.getStringExtra("LisattyTehtava"));
            subTask.setAlitehtavankuvaus(data.getStringExtra("LisattyKuvaus"));

            Subtaskname.setText(subTask.getAlitehtavannimi());
            SubtaskDescription.setText(subTask.getAlitehtavankuvaus());
        }
    }


    private void buttonState() {

        button2 = findViewById(R.id.subTaskPercent);
        if(buttonStatus == false) {
            button2.setBackgroundColor(Color.GREEN);
            button2.setText("VALMIS");
            buttonStatus = true;
        }
        else if(buttonStatus == true) {
            button2.setBackgroundColor(Color.LTGRAY);
            button2.setText("KESKEN");
            buttonStatus = false;
        }
    }

    private void setButtonStatus() {

        button2 = findViewById(R.id.subTaskPercent);
        if(buttonStatus == true) {
            button2.setBackgroundColor(Color.GREEN);
            button2.setText("VALMIS");

        }
        else if(buttonStatus == false) {
            button2.setBackgroundColor(Color.LTGRAY);
            button2.setText("KESKEN");

        }

    }

    public void sendDataBackToTasks(int poisto)
    {
        Intent intent = new Intent();
        intent.putExtra("PoistaAlitehtava",poisto);
        intent.putExtra("R_SUBTASK",subTask);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}