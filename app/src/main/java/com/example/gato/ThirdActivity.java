package com.example.gato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.Drawable;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {
    TextView texto1, texto2;
    ImageView boton;
    ImageView btn[] = new ImageView[9];

    int ticTac[] = new int[2];


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

        ticTac[0] = R.drawable.tacha;
        ticTac[1] = R.drawable.circulo;

        btn[0] = findViewById(R.id.btn_0);
        btn[1] = findViewById(R.id.btn_1);
        btn[2] = findViewById(R.id.btn_2);
        btn[3] = findViewById(R.id.btn_3);
        btn[4] = findViewById(R.id.btn_4);
        btn[5] = findViewById(R.id.btn_5);
        btn[6] = findViewById(R.id.btn_6);
        btn[7] = findViewById(R.id.btn_7);
        btn[8] = findViewById(R.id.btn_8);

        for(ImageView iv: btn){
            iv.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_0:
                btn[0].setImageResource(ticTac[0]);
                break;
            case R.id.btn_1:
                btn[1].setImageResource(ticTac[0]);
                break;
            case R.id.btn_2:
                btn[2].setImageResource(ticTac[0]);
                break;
            case R.id.btn_3:
                btn[3].setImageResource(ticTac[0]);
                break;
            case R.id.btn_4:
                btn[4].setImageResource(ticTac[0]);
                break;
            case R.id.btn_5:
                btn[5].setImageResource(ticTac[0]);
                break;
            case R.id.btn_6:
                btn[6].setImageResource(ticTac[0]);
                break;
            case R.id.btn_7:
                btn[7].setImageResource(ticTac[0]);
                break;
            case R.id.btn_8:
                btn[8].setImageResource(ticTac[0]);
                break;
        }


    }
}