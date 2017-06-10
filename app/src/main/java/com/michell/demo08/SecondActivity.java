package com.michell.demo08;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by MICHELL on 10/06/2017.
 */

public class SecondActivity extends AppCompatActivity {

    private EditText etNombre, etApellido;
    private Button btActualizar, btEliminar;
    private int DELETE_CODE = 10;

    private final View.OnClickListener btEliminarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
            builder.setTitle("Mensaje");
            builder.setMessage("¿Desea eliminar?");

            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent = new Intent();
                    intent.putExtra("nombre", etNombre.getText().toString());
                    intent.putExtra("apellido", etApellido.getText().toString());

                    setResult(DELETE_CODE, intent);
                    finish();

                    Toast.makeText(SecondActivity.this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(SecondActivity.this, "Canceló operación", Toast.LENGTH_SHORT).show();
                }
            });

            builder.show();

        }
    };

    private final View.OnClickListener btActualizarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent();
            String nom = etNombre.getText().toString();
            String ape = etApellido.getText().toString();

            if(TextUtils.isEmpty(nom)) {
                Toast.makeText(SecondActivity.this, "Nombre vacio", Toast.LENGTH_SHORT).show();
            }
            else
                if(TextUtils.isEmpty(ape)) {
                    Toast.makeText(SecondActivity.this, "Apellido vacio", Toast.LENGTH_SHORT).show();
                }
            else {

                    intent.putExtra("nombre", etNombre.getText().toString());
                    intent.putExtra("apellido", etApellido.getText().toString());

                    setResult(RESULT_OK, intent);
                    finish();
                }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);

        btActualizar = (Button) findViewById(R.id.btActualizar);
        btEliminar = (Button) findViewById(R.id.btEliminar);

        Intent intent = getIntent();

        String nombre = intent.getStringExtra("nombre");
        String apellido  = intent.getStringExtra("apellido");
        etNombre.setText(nombre);
        etApellido.setText(apellido);

        if(nombre == null || nombre.equals("")) {
            btActualizar.setText("REGISTRAR");
            btEliminar.setEnabled(false);
        }

        btActualizar.setOnClickListener(btActualizarOnClickListener);
        btEliminar.setOnClickListener(btEliminarOnClickListener);




    }
}
