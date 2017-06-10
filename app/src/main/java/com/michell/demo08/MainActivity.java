package com.michell.demo08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spMain;
    private Button btMain;
    private SPMainAdapter mSPMainAdapter;
    private int REQUEST_CODE = 1;
    private int UPDATE_CODE = 80;
    private int posi = -1;
    private int DELETE_CODE = 10;

    private final View.OnClickListener btMainOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            startActivityForResult(intent, REQUEST_CODE);

        }
    };

    private final AdapterView.OnItemSelectedListener spMainOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if(parent.getCount() > 1) {
                if (position > 0) {
                    // POSICION DEL ITEM SELECCIONADO EN EL SPINNER
                    Persona persona = (Persona) parent.getSelectedItem();
                    //Persona persona = mSPMainAdapter.getItem(position);
                    posi = position;

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                    intent.putExtra("nombre", persona.getNombre().toString());
                    intent.putExtra("apellido", persona.getApellido().toString());

                    startActivityForResult(intent, UPDATE_CODE);
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spMain = (Spinner) findViewById(R.id.spMain);
        btMain = (Button) findViewById(R.id.btMain);

        btMain.setOnClickListener(btMainOnClickListener);
        spMain.setOnItemSelectedListener(spMainOnItemSelectedListener);

        mSPMainAdapter = new SPMainAdapter(MainActivity.this);
        spMain.setAdapter(mSPMainAdapter);


        Persona persona;

        List<Persona> lstPersonas = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            persona = new Persona();

            persona.setId(java.util.UUID.randomUUID().toString());
            persona.setNombre("-- SELECCIONE");
            persona.setApellido(" UNO --");

            lstPersonas.add(persona);
        }

        mSPMainAdapter.addAll(lstPersonas);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
        if(requestCode == REQUEST_CODE) {

                Persona persona = new Persona();
                persona.setId(java.util.UUID.randomUUID().toString());
                persona.setNombre(data.getStringExtra("nombre"));
                persona.setApellido(data.getStringExtra("apellido"));

                mSPMainAdapter.add(persona);
            }
        else
            if(requestCode == UPDATE_CODE) {

                Persona persona = mSPMainAdapter.getItem(posi);

                mSPMainAdapter.remove(persona);
                persona.setId(java.util.UUID.randomUUID().toString());
                persona.setNombre(data.getStringExtra("nombre"));
                persona.setApellido(data.getStringExtra("apellido"));

                mSPMainAdapter.insert(persona, posi);
                mSPMainAdapter.notifyDataSetChanged();

            }
        }
        else
            if(resultCode == DELETE_CODE) {

                Persona persona = mSPMainAdapter.getItem(posi);

                mSPMainAdapter.remove(persona);
                mSPMainAdapter.notifyDataSetChanged();
            }


    }
}
