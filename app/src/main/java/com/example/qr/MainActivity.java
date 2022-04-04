package com.example.qr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    Button btnScan;
    EditText txtResultado, txtTelefono;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScan = findViewById(R.id.btnScan);
        txtResultado = findViewById(R.id.txtResultado);
        txtTelefono = findViewById(R.id.txtTelefono);
        btnEnviar = findViewById(R.id.btnEnviar);

        final DeveloperDB developerBD=new DeveloperDB(getApplicationContext());

        btnScan.setOnClickListener(new View.OnClickListener(){
         @Override
            public void onClick(View view){
             IntentIntegrator integrador = new IntentIntegrator( MainActivity.this);
             integrador.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE );
             integrador.setPrompt("Lector QR");
             integrador.setCameraId(0);
             integrador.setBarcodeImageEnabled(true);
             integrador.initiateScan();
         }
        });
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeveloperDB.agregarRegistro(txtResultado.getText().toString(),txtTelefono.getText().toString());
                Toast.makeText(getApplicationContext(),"SE AGREGO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result == null){
            Toast.makeText(this, "Lector Detenido", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Lector Detenido", Toast.LENGTH_LONG).show();
            txtResultado.setText(result.getContents());
        }
        super.onActivityResult(requestCode,resultCode,data);
    }


}



