module com.example.splashscreenrebrand {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.splashscreenrebrand to javafx.fxml;
    exports com.example.splashscreenrebrand;
}