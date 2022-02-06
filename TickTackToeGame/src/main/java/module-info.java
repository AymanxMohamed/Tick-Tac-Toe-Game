module com.main.ticktacktoegame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    opens com.main.ticktacktoegame to javafx.fxml;
    exports com.main.ticktacktoegame;
}
