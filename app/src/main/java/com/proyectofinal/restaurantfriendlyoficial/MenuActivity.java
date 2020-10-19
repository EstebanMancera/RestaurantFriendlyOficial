package com.proyectofinal.restaurantfriendlyoficial;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.proyectofinal.restaurantfriendlyoficial.activity.ClienteActivity;
import com.proyectofinal.restaurantfriendlyoficial.activity.ProductoActivity;
import com.proyectofinal.restaurantfriendlyoficial.activity.VentaActivity;
import com.proyectofinal.restaurantfriendlyoficial.activity.VentaHistorialActivity;
import com.proyectofinal.restaurantfriendlyoficial.esquemaSqlite.ConexionSqliteHelper;
import com.proyectofinal.restaurantfriendlyoficial.esquemaSqlite.crud.Select;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {
    Button btneditarus, btneliminarus, btnmostarus, btnsalir, btncomidas;
    TextView nombre;
    int id = 0;
    usuario u;
    daousuario dao;
    //instanciamos el control
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    //conexion con sqlite
    ConexionSqliteHelper con ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //injeccion
        ButterKnife.bind(this);
        //cargamos el toolbar
        setSupportActionBar(toolbar);
        //crear la base de datos
        con = new ConexionSqliteHelper(MenuActivity.this);

    }

    //injectamos a los botones
    @OnClick(R.id.amIbProducto)
    public void clickProducto(){
        irActivity(ProductoActivity.class);
    }

    @OnClick(R.id.amIbCliente)
    public void clickCliente(){
        irActivity(ClienteActivity.class);
    }

    @OnClick(R.id.amIbVenta)
    public void clickVenta(){
        irActivity(VentaActivity.class);
    }

    @OnClick(R.id.amIbVentaHistorial)
    public void clickVentaHistorial(){
        irActivity(VentaHistorialActivity.class);
    }

    @OnClick(R.id.btnmostrarusu)
            public void clickMostrarusu(){
        irActivity(mostrar.class);
    }

    @OnClick(R.id.btnsalirusu)
    public void clickSalirusu(){
        irActivity(MainActivity.class);
    }


    //metodo de llamado actividades  -- Como parametro toma la clase Class<?>, ya que pasaremos diferentes actividades
    void irActivity(Class<?> paramClass){
        //intento para mostrar otras actividades
        Intent intent = new Intent(getApplicationContext(), paramClass);
        //limpiamos la cola de actividades
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //llamamos a la actividad
        startActivity(intent);
    }

    //menu lado derecho
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflamos el menu, para incluir el menu personalizado
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //opcion de menu seleccionada
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
                //click opcion de backup
            case R.id.menuBackup:
                //metodo para el backup
                Select.backup(getApplicationContext());
                Toast.makeText(this, "Backup Correcto", Toast.LENGTH_SHORT).show();
                break;

                //click opcion de restore
            case R.id.menuRestore:
                //metodo para el restore
                Select.restaurar(getApplicationContext());
                Toast.makeText(this, "Restauración exitosa", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        //cerramos conexion al destruirse la actividad
        con.close();
        //que realice su trabajo comun
        super.onDestroy();
    }




//gestion admin
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnmostrarusu:
                Intent i3= new Intent(MenuActivity.this,mostrar.class);
                startActivity(i3);
                break;
            case R.id.btnsalirusu:
                Intent i4= new Intent(MenuActivity.this,MainActivity.class);
                startActivity(i4);
                finish();
                break;

        }
    }
}
