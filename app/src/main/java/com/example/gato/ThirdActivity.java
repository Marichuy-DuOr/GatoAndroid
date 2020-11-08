package com.example.gato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    TextView texto1, texto2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        texto1 = (TextView) findViewById(R.id.texto1);
        texto2 = (TextView) findViewById(R.id.texto2);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras!=null){
            String dato = extras.getString("jugador1");
            texto1.setText(dato);
            String dato2 = extras.getString("jugador2");
            texto2.setText(dato2);
        }
    }
}