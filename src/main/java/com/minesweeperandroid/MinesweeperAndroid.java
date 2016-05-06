package com.minesweeperandroid;

import com.minesweeperandroid.view.BasicView;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MinesweeperAndroid extends MobileApplication {

    public static final String BASIC_VIEW = HOME_VIEW;

    @Override
    public void init() {
        addViewFactory(BASIC_VIEW, () -> new BasicView(BASIC_VIEW));
    }

    @Override
    public void postInit(Scene scene) {
           
        Swatch.BLUE.assignTo(scene);
        Stage stage = ((Stage) scene.getWindow());
        stage.getIcons().add(new Image(MinesweeperAndroid.class.getResourceAsStream("/icon.png")));
    }
}
