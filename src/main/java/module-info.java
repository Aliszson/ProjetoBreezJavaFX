module com.example.projetomjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projetomjavafx to javafx.fxml;
    exports com.example.projetomjavafx;
}