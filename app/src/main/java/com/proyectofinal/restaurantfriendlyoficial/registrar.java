package com.proyectofinal.restaurantfriendlyoficial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class registrar extends AppCompatActivity implements View.OnClickListener {
    EditText correo, contraseña, nombre, apellido, direccion, celular;
    Button brncrear, btncancelarreg;
    daousuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);
        correo=(EditText)findViewById(R.id.txtUsuariocrear);
        contraseña=(EditText)findViewById(R.id.txtContraseñacrear);
        nombre=(EditText)findViewById(R.id.txtNombrecrear);
        apellido=(EditText)findViewById(R.id.txtApellidocrear);
        direccion=(EditText)findViewById(R.id.txtDireccioncrear);
        celular=(EditText)findViewById(R.id.txtCelularcrear);



        brncrear=(Button)findViewById(R.id.btncrear);
        btncancelarreg=(Button)findViewById(R.id.btncancelarreg);

        brncrear.setOnClickListener(this);
        btncancelarreg.setOnClickListener(this);
        dao= new daousuario(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btncrear:
                usuario u = new usuario();
                u.setCorreo(correo.getText().toString());
                u.setContraseña(contraseña.getText().toString());
                u.setNombre(nombre.getText().toString());
                u.setApellido(apellido.getText().toString());
                u.setDireccion(direccion.getText().toString());
                u.setCelular(Integer.valueOf(celular.getText().toString()));
                //cliente.setLimiteCredito(Double.valueOf(limiteCredito.getText().toString()));
                if (!u.isNull()){
                    Toast.makeText(this,"ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                }else if(dao.insertarUsuario(u)){
                    Toast.makeText(this,"REGISTRO EXITOSO", Toast.LENGTH_LONG).show();
                    Intent i2= new Intent(registrar.this,MainActivity.class);
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this,"USUARIO EXISTENTE", Toast.LENGTH_LONG).show();
                }
                break;
            case  R.id.btncancelarreg:
                Intent i= new Intent(registrar.this,MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}