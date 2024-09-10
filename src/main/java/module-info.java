module com.example.projetomjavafx {
    requires javafx.fxml;
    requires java.sql;
    requires transitive org.controlsfx.controls;


    opens com.example.projetomjavafx to javafx.fxml;
    exports com.example.projetomjavafx;
}