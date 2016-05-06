/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minesweeperandroid.model;
import java.util.Observable;

/**
 *
 * @author p1314555
 */
public class Model extends Observable{
    private static Grille g;
    private boolean ChangedMode = false;
    
    public Model(){
        g = new Grille(0);
        g.InitialiserBombs();
        g.CompterBombs();
    }
    
    public void setDifficulty(int mode){
        g = new Grille(mode);
        g.InitialiserBombs();
        g.CompterBombs();
        ChangedMode = true;
        setChanged();
        notifyObservers();
        ChangedMode = false;
    }
    
    public boolean isDecouvert(int i, int j){
        return g.isDecouvert(i, j);
    }
    
    public boolean isFlagged(int i, int j){
        return g.isDrapeau(i, j);
    }
    
    public void setFlagged(int i, int j, boolean mode){
        g.setDrapeau(i, j, mode);
        setChanged();
        notifyObservers();
    }
    
    public int getDifficulte(){
        return g.getDifficulte();
    }
    
    public int getHauter(){
        return g.getHauter();
    }
    
    public int getLongeur(){
        return g.getLongeur();
    }
    
    public int getNbBombs(){
        return g.getNbBombs();
    }
    
    public int[][] getListBombs(){
        return g.getListBombs();
    }
    
    public int getCaseEtat(int i, int j){
        return g.getCaseEtat(i, j);
    }
    
    public void ChoisirCase(int i, int j){
        g.ChoisirCase(i, j);
        setChanged();
        notifyObservers();
    }
    
    public int JeuTermine(){
        return g.JeuTermine();
    }

    public boolean ChangedMode(){
        return ChangedMode;
    }
}
