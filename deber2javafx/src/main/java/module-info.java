module ec.edu.espol.deber2javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.deber2javafx to javafx.fxml;
    exports ec.edu.espol.deber2javafx;
    opens ec.edu.espol.controllers to javafx.fxml;
    exports ec.edu.espol.controllers;
    opens ec.edu.espol.model to javafx.fxml;
    exports ec.edu.espol.model;
}
