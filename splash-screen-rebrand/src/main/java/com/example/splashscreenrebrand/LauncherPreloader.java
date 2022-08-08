package com.example.splashscreenrebrand;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LauncherPreloader extends Preloader {

     private Stage preloaderStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.preloaderStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("initPreloader.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Splash Screen");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        if (info.getType() == StateChangeNotification.Type.BEFORE_START) {
            preloaderStage.hide();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
