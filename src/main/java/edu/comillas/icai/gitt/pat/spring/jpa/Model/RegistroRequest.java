package edu.comillas.icai.gitt.pat.spring.jpa.Model;

public class RegistroRequest {
    public String nombre;
    public  String email;
    public String credenciales;
    public  Integer piso;
    public  String letra;

    public String getNombre() {
        return nombre;
    }

    public String getEmailR() {
        return email;
    }

    public String getCredencialesR() {
        return credenciales;
    }

    public Integer getPiso() {
        return piso;
    }

    public String getLetra() {
        return letra;
    }
}
