package com.proyectofinal.restaurantfriendlyoficial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class editar extends AppCompatActivity implements View.OnClickListener{
EditText txteditCorreo, txteditContraseña, txteditNombre, txteditApellido, txteditDireccion, txteditCelular;
Button btneditactualizar, btncancelaract;
int id=0;
usuario u;
daousuario dao;
Intent x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);
        txteditCorreo=(EditText)findViewById(R.id.txteditCorreo);
        txteditContraseña=(EditText)findViewById(R.id.txteditContraseña);
        txteditNombre=(EditText)findViewById(R.id.txteditNombre);
        txteditApellido=(EditText)findViewById(R.id.txteditApellido);
        txteditDireccion=(EditText)findViewById(R.id.txteditDireccion);
        txteditCelular=(EditText)findViewById(R.id.txteditCelular);

        btneditactualizar=(Button)findViewById(R.id.btnactualizar);
        btncancelaract=(Button)findViewById(R.id.btncancelaract);
        btneditactualizar.setOnClickListener(this);
        btncancelaract.setOnClickListener(this);


        Bundle c=getIntent().getExtras();
        id=c.getInt("id");
        dao= new daousuario(this);
        u=dao.getUsuarioById(id);

        txteditCorreo.setText(u.getCorreo());
        txteditContraseña.setText(u.getContraseña());
        txteditNombre.setText(u.getNombre());
        txteditApellido.setText(u.getApellido());
        txteditDireccion.setText(u.getDireccion());
//        txteditCelular.setText(u.getCelular());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnactualizar:

                //usuario u = new usuario();
                u.setCorreo(txteditCorreo.getText().toString());
                u.setContraseña(txteditContraseña.getText().toString());
                u.setNombre(txteditNombre.getText().toString());
                u.setApellido(txteditApellido.getText().toString());
                u.setDireccion(txteditDireccion.getText().toString());
                u.setCelular(Integer.valueOf(txteditCelular.getText().toString()));
                //cliente.setLimiteCredito(Double.valueOf(limiteCredito.getText().toString()));
                if (!u.isNull()){
                    Toast.makeText(this,"ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                }else if(dao.updateUsuario(u)){
                    Toast.makeText(this,"ACTUALIZACION  EXITOSO", Toast.LENGTH_LONG).show();
                    Intent z= new Intent(editar.this,MenuActivity.class);
                    z.putExtra("id", u.getId());
                    startActivity(z);
                    finish();
                }else{
                    Toast.makeText(this,"No se pudo actualizar :c", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btncancelaract:
                Intent i4= new Intent(editar.this,MenuActivity.class);
                i4.putExtra("id", u.getId());
                startActivity(i4);
                finish();
                break;

        }
    }



}