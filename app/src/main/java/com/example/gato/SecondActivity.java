package com.example.gato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{
    EditText jugador1, jugador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        jugador1 = (EditText) findViewById(R.id.jugador1);
        jugador2 = (EditText) findViewById(R.id.jugador2);
    }

    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.btnComenzar:
                String dato1 = jugador1.getText().toString();
                String dato2 = jugador2.getText().toString();
                Intent siguiente = new Intent(SecondActivity.this, ThirdActivity.class);
                siguiente.putExtra("jugador1", dato1);
                siguiente.putExtra("jugador2", dato2);
                startActivity(siguiente);
                break;
        }
    }

}