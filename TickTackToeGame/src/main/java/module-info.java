module com.main.ticktacktoegame {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.main.ticktacktoegame to javafx.fxml;
    exports com.main.ticktacktoegame;
}
