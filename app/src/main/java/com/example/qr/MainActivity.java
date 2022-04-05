package com.example.qr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    EditText txtResultado,txtTelefono,txtCodigo;
    Button btnEnviar;
    Button btnScan;

    String bd="developer.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCodigo = (EditText) findViewById(R.id.txtCodigo);
        txtResultado = (EditText) findViewById(R.id.txtResultado);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnScan = (Button) findViewById(R.id.btnScan);

        final DeveloperDB developerDB=new DeveloperDB(getApplicationContext());
        getApplicationContext().openOrCreateDatabase(bd, MODE_PRIVATE, null);

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
                developerDB.agregarRegistros(txtCodigo.getText().toString(),txtResultado.getText().toString(),txtTelefono.getText().toString());
                Toast.makeText(getApplicationContext(),"Datos Enviados", Toast.LENGTH_SHORT).show();
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



