/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minesweeperandroid.view;

import com.minesweeperandroid.MinesweeperAndroid;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Ryuto
 */
public class Bomb extends ImageView{
    public Bomb(){
        final Image Bomb = new Image(MinesweeperAndroid.class.getResourceAsStream("/mine.png"));
        setImage(Bomb);
        setPreserveRatio(true);
        setSmooth(true);
        setCache(true);
    }
}
