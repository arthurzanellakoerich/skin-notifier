package com.arthur.model;

public class SkinAlerta {
    private String nome;
    private double precoMinimo;
    private double precoMaximo;

    public SkinAlerta(String nome, double precoMinimo, double precoMaximo) {
        this.nome = nome;
        this.precoMinimo = precoMinimo;
        this.precoMaximo = precoMaximo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoMinimo() {
        return precoMinimo;
    }

    public double getPrecoMaximo() {
        return precoMaximo;
    }


}
