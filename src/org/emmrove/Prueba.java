/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emmrove;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author emmrove
 */
public class Prueba {

    JFrame ventana = new JFrame("PacMan OpengGL");
    Graficos dl;
    Puntuacion pl;
    JPanel laberinto;
    JPanel puntaje;

    Escucha esc;

    public Prueba() {
        ventana.setBounds(60, 30, 800, 560);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        puntaje = new JPanel();
        puntaje.setLayout(null);
        pl = new Puntuacion();
        pl.setBounds(587, 0, 200, 550);
        
        dl = new Graficos(pl);
        laberinto = new JPanel();
        laberinto.setLayout(null);
        dl.setBounds(0, 0, 600, 550);

        laberinto.add(dl);
        puntaje.add(pl);

        esc = new Escucha(dl);
        esc.run();
        ventana.getContentPane().add(laberinto);
        ventana.getContentPane().add(puntaje);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        new Prueba();
    }
}
