// Classe de utilidade para exibir alertas na tela


package com.example.projetomjavafx.util;
import javafx.scene.control.Alert;

public class Alerta {
    public static void exibirAlerta(String title, String header, String content, Alert.AlertType type) {
        Alert alerta = new Alert(type);
        alerta.setTitle(title);
        alerta.setHeaderText(header);
        alerta.setContentText(content);
        alerta.show();
    }
}
