/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spdvi;

/**
 *
 * @author Alumne
 */
public class Obras {
    public String getRegistre() {
        return registre;
    }

    public void setRegistre(String registre) {
        this.registre = registre;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getAny() {
        return any;
    }

    public void setAny(String any) {
        this.any = any;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public String getimatge() {
        return imatge;
    }

    public void setimatge(String imatge) {
        this.imatge = imatge;
    }
    
    private String registre;
    private String titol;
    private String any;
    private String format;
    private String autor;
    private String imatge;

    @Override
    public String toString() {
        return registre + ": " + titol + ", " + any + " (" + format + "). " + autor + ", " + imatge; 
    }

    
}
