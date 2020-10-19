package com.proyectofinal.restaurantfriendlyoficial;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;



import java.util.ArrayList;

public class mostrar extends AppCompatActivity {
    ListView lista;
    daousuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar);

        lista=(ListView)findViewById(R.id.lista);
        dao= new daousuario(this);
        ArrayList<usuario> listusu=dao.selectusuario();
        ArrayList<String> list= new ArrayList<String>();
        for (usuario u:listusu) {
            list.add(u.getNombre()+" "+u.getApellido()+" "+u.getContraseña());

        }
        ArrayAdapter<String> a = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, list);
        lista.setAdapter(a);
    }
}