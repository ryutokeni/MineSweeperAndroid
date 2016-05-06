package com.minesweeperandroid.view;

import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.minesweeperandroid.controller.Controller;
import com.minesweeperandroid.model.Model;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BasicView extends View {
    private Pane scene = new Pane();
    private VBox MenuBoard = new VBox();
    private Pane GameScreen = new Pane();
    private boolean show = true;
    private com.minesweeperandroid.view.View v = new com.minesweeperandroid.view.View();
    private Model m = new Model();
    private Controller c = new Controller(m, v, scene);
    public BasicView(String name) {
        super(name);       
        Button button1 = new Button("Easy");
        button1.setStyle("-fx-background-color:darkblue"); 
        Button button2 = new Button("Normal");
        button2.setStyle("-fx-background-color:darkblue");
        Button button3 = new Button("Hard");
        button3.setStyle("-fx-background-color:darkblue");
        MenuBoard.setAlignment(Pos.CENTER);
        MenuBoard.setSpacing(20);
        MenuBoard.prefHeightProperty().bind(scene.heightProperty());
        MenuBoard.prefWidthProperty().bind(scene.widthProperty());
        MenuBoard.getChildren().addAll(button1,button2,button3);
        MenuBoard.setStyle("-fx-background-color:rgb(33,150,243)");        
        //MenuBoard.setFill(Color.BLUE);
        
        GameScreen.getChildren().add(v.getGrilleView());
        
        button1.setOnAction(e -> SetGameMode(0));
        button2.setOnAction(e -> SetGameMode(1));
        button3.setOnAction(e -> SetGameMode(2));
        scene.getChildren().addAll(GameScreen, MenuBoard);
        /*------------------------------------------MAIN SCREEN---------------------------------------------------*/
        m.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                if (m.ChangedMode()) {
                    c.Init(scene);
                }
                if (m.JeuTermine() != 0) {
                    c.EndGame();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    if (m.JeuTermine() == -1) {
                        alert.setContentText("Sorry, you lost the game!");
                    } else {
                        alert.setContentText("Congratulations, you won the game!");
                    }
                    alert.showAndWait();
                    c.Restart();
                }
                for (int i = 0; i < m.getHauter(); i++) {
                    for (int j = 0; j < m.getLongeur(); j++) {
                        if (m.isDecouvert(i, j)) {
                            Case.getTbCase()[i][j].OpenCase();
                        }
                    }
                }
            }
        });    
        
        
        //scene.setAlignment(Pos.CENTER);
        
        setCenter(scene);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> ShowMenu()));
        appBar.setTitleText("Basic View");
    }
    
    private void ShowMenu(){
        show=true;
        MenuBoard.setVisible(show);
    }
    
    private void SetGameMode(int mode){
        show = !show;
        MenuBoard.setVisible(show);
        m.setDifficulty(mode);
    }
    
}
