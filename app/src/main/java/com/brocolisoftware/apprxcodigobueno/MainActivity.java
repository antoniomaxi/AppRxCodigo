package com.brocolisoftware.apprxcodigobueno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton buttonIntroduccion;
    private AppCompatButton buttonDisposable;
    private AppCompatButton buttonCompositeDisposable;
    private AppCompatButton buttonOperators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUIView();
    }

    private void setUIView(){

        buttonIntroduccion = findViewById(R.id.ButtonIntroduccion);
        buttonDisposable = findViewById(R.id.ButtonDisposable);
        buttonCompositeDisposable = findViewById(R.id.ButtonCompositeDisposable);
        buttonOperators = findViewById(R.id.ButtonOperators);
        buttonIntroduccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PruebaIntroduccionActivity.class));
            }
        });

        buttonDisposable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DisposableActivity.class));
            }
        });

        buttonCompositeDisposable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CompositeDisposableActivity.class));
            }
        });

        buttonOperators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),OperatorsActivity.class));
            }
        });


    }

}