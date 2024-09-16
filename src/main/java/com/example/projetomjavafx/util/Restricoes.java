package com.example.projetomjavafx.util;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class Restricoes {
    public static void DURACAO(TextField textField) {
        UnaryOperator<TextFormatter.Change> mascaraDuracao = change -> {
            String texto = change.getControlNewText().replaceAll("[^\\d]", "");

            StringBuilder duracaoFormatada = new StringBuilder(texto);

            if (texto.length() > 6){
                return null;
            }

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


    public static void verificaNome(TextField campTexto) {
        UnaryOperator<TextFormatter.Change> mascaraNome = change -> {
            String Texto = change.getControlNewText();

            if (Texto.matches("[a-zA-Z0-9]*")) {
                if (Texto.length() > 20) {
                    return null;
                }
                return change;
            } else {
                return null;
            }
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(mascaraNome);
        campTexto.setTextFormatter(textFormatter);
    }

    public static void verificaSenha(PasswordField campoSenha) {
        UnaryOperator<TextFormatter.Change> mascaraSenha = change -> {
            String Texto = change.getControlNewText();

            if (Texto.matches("[^\\s]*")) {
                if (Texto.length() > 20) {
                    return null;
                }
                return change;
            } else {
                return null;
            }
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(mascaraSenha);
        campoSenha.setTextFormatter(textFormatter);
    }


    public static void verificaBio(TextArea areaTexto) {
        UnaryOperator<TextFormatter.Change> mascaraBio = change -> {
            String Texto = change.getControlNewText();

            if (Texto.length() > 280) {
                    return null;
                }
                return change;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(mascaraBio);
        areaTexto.setTextFormatter(textFormatter);
    }

    public static void COMENTARIO(TextArea areaTexto) {

        int maxCharLinha = 40;

        UnaryOperator<TextFormatter.Change> mascaraComent = change -> {
            String texto = change.getControlNewText();
            int posicaoOriginalCursor = change.getCaretPosition();

            // calculando posicao da linha atual
            int inicioLinhaAtual = texto.lastIndexOf("\n", posicaoOriginalCursor - 1) + 1;
            int tamanhoLinhaAtual = posicaoOriginalCursor - inicioLinhaAtual;

            // verificar se a linha excede o limite de caracteres
            if (tamanhoLinhaAtual >= maxCharLinha){
                // inserir quebra de linha antes do novo caractere
                change.setText("\n");
            }
            return change;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(mascaraComent);
        areaTexto.setTextFormatter(textFormatter);
    }


    public static void verificaTituloMusica(TextField campTexto) {
        UnaryOperator<TextFormatter.Change> mascaraTituloMusica = change -> {
            String Texto = change.getControlNewText();

            if (Texto.length() > 30) {
                return null;
            }
            return change;
        };


        TextFormatter<String> textFormatter = new TextFormatter<>(mascaraTituloMusica);
        campTexto.setTextFormatter(textFormatter);
    }


    public static void verificaLetra(TextArea areaTexto) {
        UnaryOperator<TextFormatter.Change> mascaraLetra = change -> {
            String Texto = change.getControlNewText();

            if (Texto.length() > 500) {
                return null;
            }
            return change;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(mascaraLetra);
        areaTexto.setTextFormatter(textFormatter);
    }
}