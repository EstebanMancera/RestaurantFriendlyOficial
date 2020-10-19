package com.proyectofinal.restaurantfriendlyoficial;

public class usuario {

    int id;
    String correo, contraseña,  nombre, apellido, direccion;
    int  celular;
    public usuario() {
    }

    public usuario(String correo, String contraseña, String nombre, String apellido, String direccion, int celular) {
        this.correo = correo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.celular = celular;
    }

    public boolean isNull(){
        if (correo.equals("")&&contraseña.equals("")&&nombre.equals("")&&apellido.equals("")&&direccion.equals("")){
            return false;

        }else{
            return true;
        }
    }

    @Override
    public String toString() {
        return "usuario{" +
                "id=" + id +
                ", correo='" + correo + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", celular=" + celular +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
