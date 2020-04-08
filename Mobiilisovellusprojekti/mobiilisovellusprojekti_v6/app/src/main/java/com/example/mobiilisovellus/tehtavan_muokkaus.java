package com.example.mobiilisovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class tehtavan_muokkaus extends AppCompatActivity {

    Button saveBtn;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tehtavan_muokkaus);
        findViewById(R.id.addTaskname);

        findViewById(R.id.saveTask).setOnClickListener(buttonClick2);
       // findViewById(R.id.saveTask).setOnClickListener(buttonClick2);
        //findViewById(R.id.backButton).setOnClickListener(buttonClick2);


    }
/*saveBtn.setOnClickListener(new View.OnClickListener() {             //Lähettää TehtavaOsioPaanakymalle tehtävän otsikon
        @Override
        public void onClick(View view) {
            EditText headline = (EditText) findViewById(R.id.addTaskname);
            String headlineMessage = headline.getText().toString();
            Intent intent = new Intent(view.getContext(), TehtavaOsioPaanakyma.class);

            intent.putExtra("msg", headlineMessage);
            startActivity(intent);
            finish();
        }
    });*/

    public View.OnClickListener buttonClick2 = (new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.saveTask:
                    EditText headline = (EditText) findViewById(R.id.addTaskname);
                    String headlineMessage = headline.getText().toString();
                    Intent intent = new Intent(tehtavan_muokkaus.this, TehtavaOsioPaanakyma.class);

                    intent.putExtra("msg", headlineMessage);                                                    // Tarkoitus lähettää Edittext otsikko TehtäväOsioPaanakymaan
                    startActivity(intent);
                    finish();

                    /*Intent intent = new Intent(tehtavan_muokkaus.this, TehtavaOsioPaanakyma.class);
                    String headlineMessage = headline.getText().toString();
                    intent.putExtra("msg", headlineMessage);
                    startActivity(intent);
                    //finish();
                    break;*/
            }

        }




    });
}


