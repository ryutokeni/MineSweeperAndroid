/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minesweeperandroid.view;

import javafx.scene.layout.GridPane;

import javafx.scene.text.Text;


/**
 *
 * @author Ryuto
 */
public class View{
    private int Bombs = 0;
    private Text bombCounter = new Text();
    private GridPane GrilleView = new GridPane();
    
    public View(){
        GrilleView.setStyle("-fx-background-color:White");        
        GrilleView.setGridLinesVisible(true);      
    }
    
    public void setBombCounter(int bombs){
        Bombs = bombs;
        bombCounter.setText(Integer.toString(bombs));
    }
    
    public void DecreaseBombs(){
        bombCounter.setText(Integer.toString(--Bombs));
    }
    
    public void IncreaseBombs(){
        bombCounter.setText(Integer.toString(++Bombs));
    }

    public GridPane getGrilleView(){
        return GrilleView;
    }
}
