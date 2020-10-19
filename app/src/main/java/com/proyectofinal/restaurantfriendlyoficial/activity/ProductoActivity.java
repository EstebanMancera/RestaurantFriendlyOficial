package com.proyectofinal.restaurantfriendlyoficial.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyectofinal.restaurantfriendlyoficial.R;
import com.proyectofinal.restaurantfriendlyoficial.adaptador.ProductoItemRecycler;
import com.proyectofinal.restaurantfriendlyoficial.data.modelo.Producto;
import com.proyectofinal.restaurantfriendlyoficial.esquemaSqlite.crud.Select;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductoActivity extends AppCompatActivity {

    @BindView(R.id.apRvProducto)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.apEtBuscarProducto)
    EditText buscador;

    RecyclerView.LayoutManager layoutManager;
    ProductoItemRecycler adapter;

    List<Producto> listaProducto = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        layoutManager = new LinearLayoutManager(getApplicationContext());

        cargarLista();

        buscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cargarLista();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void cargarLista() {
        Select.seleccionarProductos(getApplicationContext(), listaProducto , buscador.getText().toString());
        cargarRecycler(listaProducto);
    }

    private void cargarRecycler(List<Producto> listaProducto) {

        adapter = new ProductoItemRecycler(listaProducto, new ProductoItemRecycler.OnItemClickListener() {
            @Override
            public void OnClickItem(Producto producto, int posicion) {
                irActivity(false, producto);
            }
        });

        recyclerView.setHasFixedSize(true);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

    }

    @OnClick(R.id.apFabNuevoProducto)
    public void clickNuevoProducto()
    {
        irActivity(true, new Producto());
    }

    void irActivity( boolean bNuevo, Producto itemProducto){
        Intent intent = new Intent(getApplicationContext(), ProductoDetalleActivity.class);

        intent.putExtra("bNuevo", bNuevo);
        intent.putExtra("itemProducto", itemProducto);

        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) cargarLista();
    }
}