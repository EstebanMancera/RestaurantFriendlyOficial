package com.proyectofinal.restaurantfriendlyoficial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daousuario {

    Context c;
    usuario u;
    ArrayList<usuario> lista;
    SQLiteDatabase sql;
    String bd="DBusuario";
    String tabla = "create table if not exists  usuario(id integer primary key autoincrement, correo text, contraseña text, nombre text, apellido text, direccion text, celular numeric)";

    public daousuario(Context c){
        this.c=c;
        sql = c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u=new usuario();
    }

    public boolean insertarUsuario(usuario u){
        if(buscar(u.getCorreo())==0){
            ContentValues cv=new ContentValues();
            cv.put("correo",u.getCorreo());
            cv.put("contraseña",u.getContraseña());
            cv.put("nombre",u.getNombre());
            cv.put("apellido",u.getApellido());
            cv.put("direccion",u.getDireccion());
            cv.put("celular",u.getCelular());
            return (sql.insert("usuario", null,cv)>0);
        }else{
            return false;
        }
    }
    public int buscar (String u){
        int  x=0;
        lista = selectusuario();
        for(usuario us:lista){
            if(us.getCorreo().equals(u)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<usuario> selectusuario(){
        ArrayList<usuario> lista = new ArrayList<usuario>();
        lista.clear();
        Cursor cr=sql.rawQuery("select * from usuario", null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                usuario u=new usuario();
                u.setId(cr.getInt(0));
                u.setCorreo(cr.getString(1));
                u.setContraseña(cr.getString(2));
                u.setNombre(cr.getString(3));
                u.setApellido(cr.getString(4));
                u.setDireccion(cr.getString(5));
                u.setCelular(cr.getInt(6));
                lista.add(u);
            }while(cr.moveToNext());

        }
        return lista;
    }
    public int login (String u, String p){
        int a = 0;
        Cursor cr=sql.rawQuery("select * from usuario", null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                if(cr.getString(1).equals(u)&&cr.getString(2).equals(p)){
                    a++;
                }
            }while(cr.moveToNext());

        }
        return a;

    }

    public usuario getUsuario(String u, String p){
        lista=selectusuario();
        for (usuario us:lista) {
            if (us.getCorreo().equals(u)&&us.getContraseña().equals(p)){
                return us;
            }
        }
        return null;
    }

    public usuario getUsuarioById(int id){
        lista=selectusuario();
        for (usuario us:lista) {
            if (us.getId()==id){
                return us;
            }
        }
        return null;
    }

    public boolean updateUsuario(usuario u){
        ContentValues cv=new ContentValues();
        cv.put("correo",u.getCorreo());
        cv.put("contraseña",u.getContraseña());
        cv.put("nombre",u.getNombre());
        cv.put("apellido",u.getApellido());
        cv.put("direccion",u.getDireccion());
        cv.put("celular",u.getCelular());
        return (sql.update("usuario",cv,"id"+ u.getId(),null)>0);
    }
    public boolean deleteUsuario(int id){
        return (sql.delete("usuario","id="+id,null)>0);
    }


}
