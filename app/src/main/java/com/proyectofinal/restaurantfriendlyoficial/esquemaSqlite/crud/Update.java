package com.proyectofinal.restaurantfriendlyoficial.esquemaSqlite.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.proyectofinal.restaurantfriendlyoficial.data.modelo.Cliente;
import com.proyectofinal.restaurantfriendlyoficial.data.modelo.Producto;
import com.proyectofinal.restaurantfriendlyoficial.esquemaSqlite.ConexionSqliteHelper;
import com.proyectofinal.restaurantfriendlyoficial.esquemaSqlite.tablas.ClienteTabla;
import com.proyectofinal.restaurantfriendlyoficial.esquemaSqlite.tablas.ProductoTabla;

public class Update {

    public static void actualizar(Context context, Object param, String tabla) {

    ConexionSqliteHelper con = new ConexionSqliteHelper(context);

    SQLiteDatabase db = con.getWritableDatabase();

    ContentValues values = new ContentValues();

    switch (tabla)
    {
        case ClienteTabla.TABLA:
            Cliente cliente = (Cliente)param;

            values.put(ClienteTabla.CLIE_NOMBRE,cliente.getClie_nombre());
            values.put(ClienteTabla.CLIE_NUM_CEL,cliente.getClie_num_cel());
            values.put(ClienteTabla.CLIE_EMAIL,cliente.getClie_email());
            values.put(ClienteTabla.CLIE_DIRECCION,cliente.getClie_direccion());

            db.update(ClienteTabla.TABLA, values, ClienteTabla.CLIE_ID + " = ?" , new String[] {String.valueOf(cliente.getClie_id())});

            break;

        case ProductoTabla.TABLA:

            Producto prod = (Producto)param;

            values.put(ProductoTabla.PROD_NOMBRE,prod.getProd_nombre());
            values.put(ProductoTabla.PROD_PRECIO,prod.getProd_precio());
            values.put(ProductoTabla.PROD_RUTA_FOTO,prod.getProd_ruta_foto());

            db.update(ProductoTabla.TABLA,values, ProductoTabla.PROD_ID + " = ?", new String[] {String.valueOf(prod.getProd_id())});

            break;
    }
}
}
