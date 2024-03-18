package com.myFuzzyProject;

public class Datos {
    private double calificacion;
    private double genero;
    private double resultado;
    private double ano;

    public Datos(double calificacion, double genero, double ano, double resultado) {
        this.calificacion = calificacion;
        this.genero = genero;
        this.resultado = resultado;
        this.ano = ano;
    }

    // Getters y setters
    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public double getAno() {
        return ano;
    }

    public void setAno(double ano) {
        this.ano = ano;
    }

    public double getGenero() {
        return genero;
    }

    public void setGenero(double genero) {
        this.genero = genero;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
}