package com.example.splashscreenrebrand;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    public static Stage primaryStage = null;

    @Override
    public void start(Stage stage) throws IOException {
        MainApplication.primaryStage = stage;
    }

    @Override
    public void init() throws Exception {
        InitPreloader init = new InitPreloader();
        init.checkFunction();
    }

    public static void main(String[] args) {
        System.setProperty("javafx.preloader", LauncherPreloader.class.getCanonicalName());
        launch(args);
    }
}
