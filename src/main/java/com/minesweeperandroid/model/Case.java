/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minesweeperandroid.model;

/**
 *
 * @author p1006126
 */
public class Case {

    private boolean decouvert;
    private boolean drapeau;
    private int etat; //piegee=-1
    Grille g;

    public Case() {
        etat = 0;
        decouvert = false;
        drapeau = false;
    }

    public void setEtat(int e) {
        etat = e;
    }

    public int getEtat() {
        return etat;
    }

    public void setMine() {
        etat = -1;
    }

    public void setDecouvert() {
        decouvert = true;
    }

    public boolean isDecouvert() {
        return decouvert;
    }
    
    public void setDrapeau(boolean mode){
        drapeau = mode;
    }

    public boolean isDrapeau() {
        return drapeau;
    }

    public boolean isPiegee() {
        return (etat == -1);
    }
}
