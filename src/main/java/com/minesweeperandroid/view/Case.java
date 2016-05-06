/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minesweeperandroid.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author p1314555
 */
public class Case extends StackPane {

    private static Case tbCase[][];

    private int x, y;
    private boolean decouvert = false;
    private Text text = new Text();
    private Rectangle rect;
    private Bomb bomb;
    private Drapeau drapeau;

    public static void Init(int i, int j) {
        tbCase = new Case[i][j];
    }

    public Case(Pane scene, int x, int y, int etat, int height, int width) {

        this.x = x;
        this.y = y;
        int temp = etat;
        rect = new Rectangle();
        rect.heightProperty().bind(scene.heightProperty().subtract(20).divide(height));
        rect.widthProperty().bind(scene.widthProperty().subtract(15).divide(width));
        rect.setStroke(Color.WHITE);

        if (temp > 0) {
            text = new Text(Integer.toString(temp));
        }
        switch (temp) {
            case 1:
                text.setFill(Color.BLUE);
                break;
            case 2:
                text.setFill(Color.GREEN);
                break;
            case 3:
                text.setFill(Color.RED);
                break;
            case 4:
                text.setFill(Color.DARKBLUE);
                break;
            case 5:
                text.setFill(Color.BROWN);
                break;
        }
        text.wrappingWidthProperty().bind(rect.widthProperty());
        //Double.min(scene.getHeight(), scene)

        text.setFont(Font.font("Verdana", 15));
        text.setTextAlignment(TextAlignment.CENTER);
        getChildren().addAll(text, rect);
        tbCase[x][y] = this;
    }

    public Rectangle getRect() {
        return rect;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Case[][] getTbCase() {
        return tbCase;
    }

    public void OpenCase() {
        if (!decouvert) {
            rect.setVisible(false);
            text.setVisible(true);
            decouvert = true;
        }
    }

    public void OpenMine() {
        decouvert = true;
        bomb = new Bomb();
        bomb.fitWidthProperty().bind(rect.widthProperty());
        bomb.fitHeightProperty().bind(rect.heightProperty());
        getChildren().add(bomb);
    }

    public void placeFlag() {
        drapeau = new Drapeau();
        drapeau.fitWidthProperty().bind(rect.widthProperty());
        drapeau.fitHeightProperty().bind(rect.heightProperty());
        getChildren().add(drapeau);
    }

    public void removeFlag() {
        getChildren().remove(drapeau);

    }
}
