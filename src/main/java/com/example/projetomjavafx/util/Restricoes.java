package com.example.projetomjavafx.util;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class Restricoes {
    public static void DURACAO(TextField textField) {
        textField.setPromptText("00:00:00");
        UnaryOperator<TextFormatter.Change> mascaraDuracao = change -> {
            String texto = change.getControlNewText().replaceAll("[^\\d]", "");

            if (texto.length() > 6){
                return null;
            }

            StringBuilder duracaoFormatada = new StringBuilder(texto);

            if(texto.length() > 2){
                duracaoFormatada.insert(2,':');
            }
            if(texto.length() > 4){
                duracaoFormatada.insert(5,':');
            }

            int posicaoOriginalCursor = change.getCaretPosition();
            change.setText(duracaoFormatada.toString());
            change.setRange(0, change.getControlText().length()); // substituindo o texto
            change.selectRange(Math.min(posicaoOriginalCursor + 1, duracaoFormatada.length()), Math.min(posicaoOriginalCursor + 1, duracaoFormatada.length()));

            return change;
        };
        TextFormatter<String> textFormatter =new TextFormatter<>(mascaraDuracao);
        textField.setTextFormatter(textFormatter);
    }
}

