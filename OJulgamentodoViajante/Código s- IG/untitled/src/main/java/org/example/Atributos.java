package org.example;

public class Atributos {
    private int saude;
    private int honra;
    private int dinheiro;

    public Atributos() {
        this.saude = 50;
        this.honra = 50;
        this.dinheiro = 30;
    }

    public int getSaude() { return saude; }
    public int getHonra() { return honra; }
    public int getDinheiro() { return dinheiro; }

    public void addSaude(int valor) { saude += valor; }
    public void addHonra(int valor) { honra += valor; }
    public void addDinheiro(int valor) { dinheiro += valor; }

    public void exibir() {
        System.out.printf("Saúde: %d | Honra: %d | Dinheiro: %d\n", saude, honra, dinheiro);
    }

    public boolean gameOver() {
        return saude <= 0 || honra <= 0 || dinheiro <= 0
                || saude >= 100 || honra >= 100 || dinheiro >= 100;
    }
}
