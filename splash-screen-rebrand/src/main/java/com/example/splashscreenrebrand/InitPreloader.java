package com.example.splashscreenrebrand;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InitPreloader implements Initializable {
    public Label loading;
    public static Label loadingAuto;

    public Rectangle path;
    public Rectangle scrollBar;

    //Splash screen image.
    public ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadingAuto = loading;
        imageView.setImage(new Image(getClass().getResourceAsStream("splashscreen.jpg")));

        double sceneWidth = 530;
        double scrollBarWidth = scrollBar.getLayoutBounds().getWidth();

        // Create the initial and final key frames
        KeyValue initKeyValue =
                new KeyValue(scrollBar.translateXProperty(), -1.0 * scrollBarWidth);
        KeyFrame initFrame = new KeyFrame(Duration.ZERO, initKeyValue);

        KeyValue endKeyValue =
                new KeyValue(scrollBar.translateXProperty(), sceneWidth );
        KeyFrame endFrame = new KeyFrame(Duration.seconds(5), endKeyValue);

        // Create a Timeline object
        Timeline timeline = new Timeline(initFrame, endFrame);

        // Let the animation run forever
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Start the animation
        timeline.play();
    }

    public String checkFunction(){

        final String[] message = {""};
        Thread t1 = new Thread(() -> {
            message[0] = "Loading...";
            Platform.runLater(() -> loadingAuto.setText(message[0]) );
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            message[0] = "Initializing...";
            Platform.runLater(() -> loadingAuto.setText(message[0]) );
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            message[0] = "Starting application...";
            Platform.runLater(() -> loadingAuto.setText(message[0]) );
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            Platform.runLater(() -> {
                try {
                    Thread.sleep(2000);
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                }
                catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            });

        });

        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return message[0];
    }

}
