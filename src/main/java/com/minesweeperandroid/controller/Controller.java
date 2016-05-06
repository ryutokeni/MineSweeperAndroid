/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minesweeperandroid.controller;

import com.minesweeperandroid.view.Case;
import com.minesweeperandroid.model.Model;
import com.minesweeperandroid.view.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author p1314555
 */
public class Controller{

    static Model model;
    static View view;
    static boolean started = false;
    public Controller(Model model, View view, Pane scene) {
        this.model = model;
        this.view = view;
        Init(scene);
    }

    public void Init(Pane scene) {
        view.setBombCounter(model.getNbBombs());
        Node node = view.getGrilleView().getChildren().get(0);
        view.getGrilleView().getChildren().clear();
        view.getGrilleView().getChildren().add(0, node);
        OnCaseClick(model.getHauter(), model.getLongeur(), view.getGrilleView(), scene);
    }
    
    public void Restart(){
        model.setDifficulty(model.getDifficulte());
        view.setBombCounter(model.getNbBombs());
        started = false;
    }

    public void EndGame() {
        int nbMines = model.getNbBombs();
        int[][] listMines = model.getListBombs();
        for (int i = 0; i < nbMines; i++){
            Case.getTbCase()[listMines[i][0]][listMines[i][1]].OpenMine();
        }
    }

    public void OnCaseClick(int x, int y, GridPane GrilleView, Pane scene) {
        Case.Init(x, y);
        for (int i = 0; i < model.getHauter(); i++) {
            for (int j = 0; j < model.getLongeur(); j++) {
                Case c = new Case(scene, i, j, model.getCaseEtat(i, j), model.getHauter(), model.getLongeur());
                GrilleView.add(c, j, i);
                c.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        if (!model.isFlagged(c.getX(), c.getY())) {
                            c.OpenCase();
                            model.ChoisirCase(c.getX(), c.getY());
                        }
                    }
                });
            }
        }
    }
}
