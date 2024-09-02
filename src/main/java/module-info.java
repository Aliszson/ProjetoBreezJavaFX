module com.example.projetomjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.projetomjavafx to javafx.fxml;
    exports com.example.projetomjavafx;
}