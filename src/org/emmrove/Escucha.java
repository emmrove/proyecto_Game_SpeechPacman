/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emmrove;

/**
 *
 * @author emmrove
 */
import javax.speech.*;
import javax.speech.recognition.*;
import java.io.FileReader;
import java.util.Locale;

public class Escucha extends ResultAdapter implements Runnable {

    Graficos game;
    Recognizer recognizer;
    String gst;

    public Escucha(Graficos jue) {
        this.game = jue;
    }
    
    @Override
    public void resultAccepted(ResultEvent re) {
        try {
            Result res = (Result) (re.getSource());
            ResultToken tokens[] = res.getBestTokens();

            for (int i = 0; i < tokens.length; i++) {
                gst = tokens[i].getSpokenText();

                if (gst.equalsIgnoreCase("Arriba")) {
                    game.sentido(1);
                    System.out.println("Arriba");
                }
                if (gst.equalsIgnoreCase("Derecha")) {
                    game.sentido(2);
                    System.out.println("Derecha");
                }
                if (gst.equalsIgnoreCase("Abajo")) {
                    game.sentido(3);
                    System.out.println("Abajo");
                }
                if (gst.equalsIgnoreCase("Izquierda")) {
                    game.sentido(4);
                    System.out.println("Izquierda");
                }

            }
        } catch (Exception ex) {
            System.out.println("Ha ocurrido algo inesperado " + ex);
        }
    }

    public void reconocerVoz() {
        try {
            recognizer = Central.createRecognizer(new EngineModeDesc(Locale.ROOT));
            recognizer.allocate();

            FileReader gramatica = new FileReader("archivo/SimpleGrammarES2Copia.txt");

            RuleGrammar reglaGramatica = recognizer.loadJSGF(gramatica);
            reglaGramatica.setEnabled(true);

            recognizer.addResultListener(new Escucha(game));
            recognizer.commitChanges();
            recognizer.requestFocus();

        } catch (Exception e) {
            System.out.println("Error en " + e.toString());
        }
    }

    @Override
    public void run() {
        this.reconocerVoz();
    }
}
