package com.example.gato;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {
    TextView texto1, texto2, puntos1, puntos2;
    Button reiniciar, salir;

    String [] jugadores = new String[2];
    int [] puntos = new int[10];
    ImageView [][] btn = new ImageView[3][3]; // botones de las imagenes del juego
    ImageView [][] raya = new ImageView[3][3]; //imagenes para marcar cuando ganas

    int [] ticTac = new int[6]; // imagenes de equis, circulo y las rayas al ganar
    int [][] gato = new int[3][3]; // el juego

    int turno; // para saber si poner tacha o circulo
    int [] posicion= new int[2]; // variables de apoyo para trabajar las matrices del juego

    boolean ganar = false; // evita seguir poniendo figuras después de ganar


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        texto1 = (TextView) findViewById(R.id.texto1);
        texto2 = (TextView) findViewById(R.id.texto2);
        puntos1 = (TextView) findViewById(R.id.puntos1);
        puntos2 = (TextView) findViewById(R.id.puntos2);
        reiniciar = (Button) findViewById(R.id.btnReiniciar);
        salir = (Button) findViewById(R.id.btnSalir);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        puntos[0] = 0;
        puntos[1] = 0;
        jugadores[0] = "";
        jugadores[1] = "";

        if(extras!=null){
            jugadores[0] = extras.getString("jugador1");
            texto1.setText(jugadores[0]);
            jugadores[1] = extras.getString("jugador2");
            texto2.setText(jugadores[1]);
        }

        ticTac[0] = R.drawable.tacha;
        ticTac[1] = R.drawable.circulo;
        ticTac[2] = R.drawable.vertical;
        ticTac[3] = R.drawable.horizontal;
        ticTac[4] = R.drawable.diagonal1;
        ticTac[5] = R.drawable.diagonal2;

        btn[0][0] = findViewById(R.id.btn_0);
        btn[0][1] = findViewById(R.id.btn_1);
        btn[0][2] = findViewById(R.id.btn_2);
        btn[1][0] = findViewById(R.id.btn_3);
        btn[1][1] = findViewById(R.id.btn_4);
        btn[1][2] = findViewById(R.id.btn_5);
        btn[2][0] = findViewById(R.id.btn_6);
        btn[2][1] = findViewById(R.id.btn_7);
        btn[2][2] = findViewById(R.id.btn_8);


        raya[0][0] = findViewById(R.id.raya_0);
        raya[0][1] = findViewById(R.id.raya_1);
        raya[0][2] = findViewById(R.id.raya_2);
        raya[1][0] = findViewById(R.id.raya_3);
        raya[1][1] = findViewById(R.id.raya_4);
        raya[1][2] = findViewById(R.id.raya_5);
        raya[2][0] = findViewById(R.id.raya_6);
        raya[2][1] = findViewById(R.id.raya_7);
        raya[2][2] = findViewById(R.id.raya_8);

        for(int i = 0; i < 3; i++ ){
            for(int j = 0; j < 3; j++ ){
                btn[i][j].setOnClickListener(this);
            }
        }

        // inicializa las variables
        for (int i = 0; i < 3; i++){ // el dos equivale a espacio libre en la matriz
            for (int j = 0; j < 3; j++){
                gato[i][j] = 2;
            }
        }

        turno = 0;
        posicion[0] = 0;
        posicion[1] = 0;

        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = getIntent();
                finish();
                startActivity(mIntent);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (!ganar){
            switch (view.getId()){ //guarda las posiciones según el boton que toca
                case R.id.btn_0:
                    posicion[0] = 0;
                    posicion[1] = 0;
                    break;
                case R.id.btn_1:
                    posicion[0] = 0;
                    posicion[1] = 1;
                    break;
                case R.id.btn_2:
                    posicion[0] = 0;
                    posicion[1] = 2;
                    break;
                case R.id.btn_3:
                    posicion[0] = 1;
                    posicion[1] = 0;
                    break;
                case R.id.btn_4:
                    posicion[0] = 1;
                    posicion[1] = 1;
                    break;
                case R.id.btn_5:
                    posicion[0] = 1;
                    posicion[1] = 2;
                    break;
                case R.id.btn_6:
                    posicion[0] = 2;
                    posicion[1] = 0;
                    break;
                case R.id.btn_7:
                    posicion[0] = 2;
                    posicion[1] = 1;
                    break;
                case R.id.btn_8:
                    posicion[0] = 2;
                    posicion[1] = 2;
                    break;
                default:
                    posicion[0] = 3;
            }

            if (posicion[0] != 3){ // no se si es necesario que valide el dafault, pero por si acaso
                if(gato[posicion[0]][posicion[1]] == 2){ // revisa que la posición este libre
                    gato[posicion[0]][posicion[1]] = turno; // llena la matriz del juego
                    revisar(); // revisa si gana el juego
                    btn[posicion[0]][posicion[1]].setImageResource(ticTac[turno]); // cambia la imagen según el turno del jugador
                    if ( turno == 0){ // cambia el turno
                        turno = 1;
                    } else {
                        turno = 0;
                    }
                }
            }
        }else{
            limpiar();
            ganar=false;
        }
    }


    public void revisar (){
        for (int i = 0; i < 3; i++){
            if (gato[i][0] == gato[i][1] && gato[i][1] == gato[i][2] && gato[i][0] != 2){ // ganar en vertical
                raya[i][0].setImageResource(ticTac[3]);
                raya[i][1].setImageResource(ticTac[3]);
                raya[i][2].setImageResource(ticTac[3]);
                ganar = true;
                mostrarFelicitacion();
            }
            if (gato[0][i] == gato[1][i] && gato[1][i] == gato[2][i] && gato[0][i] != 2){ // ganar en horizontal
                raya[0][i].setImageResource(ticTac[2]);
                raya[1][i].setImageResource(ticTac[2]);
                raya[2][i].setImageResource(ticTac[2]);
                ganar = true;
                mostrarFelicitacion();
            }
        }
        if (gato[0][0] == gato[1][1] && gato[2][2] == gato[1][1] && gato[1][1] != 2){ // ganar en diagonal 1
            raya[0][0].setImageResource(ticTac[4]);
            raya[1][1].setImageResource(ticTac[4]);
            raya[2][2].setImageResource(ticTac[4]);
            ganar = true;
            mostrarFelicitacion();
        }
        if (gato[0][2] == gato[1][1] && gato[1][1] == gato[2][0] && gato[1][1] != 2){ // ganar en diagonal 2
            raya[0][2].setImageResource(ticTac[5]);
            raya[1][1].setImageResource(ticTac[5]);
            raya[2][0].setImageResource(ticTac[5]);
            ganar = true;
            mostrarFelicitacion();

        }
    }

    public void mostrarFelicitacion(){
        mostrarPuntos();
        Toast toast = Toast.makeText(getApplicationContext(), "Ganaste " + jugadores[turno] + " Felicidades!! \n \n Da clic aquí para seguir jugando" , Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }

    public void mostrarPuntos(){
        if (turno == 0){
            puntos[0] = puntos[0] + 1;
            puntos1.setText(String.valueOf(puntos[0]));
        }else {
            puntos[1] = puntos[1] + 1;
            puntos2.setText(String.valueOf(puntos[1]));
        }
    }

    public void limpiar(){

        turno = 0;
        posicion[0] = 0;
        posicion[1] = 0;

        for (int i = 0; i < 3; i++){ // el dos equivale a espacio libre en la matriz
            for (int j = 0; j < 3; j++){
                gato[i][j] = 2;
            }
        }

        for(int i = 0; i < 3; i++ ){
            for(int j = 0; j < 3; j++ ){
                btn[i][j].setImageResource(R.drawable.transparente);
                raya[i][j].setImageResource(R.drawable.transparente);
            }
        }


    }
}