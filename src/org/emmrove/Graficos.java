/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emmrove;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.media.opengl.*;
import javax.swing.JOptionPane;

public class Graficos extends GLCanvas implements GLEventListener, KeyListener {

    boolean cambio = false;
    Toolkit kit;
    Dimension dimensionPantalla;
    int altura;
    int anchura;
    int puntuacion = 0;
    int sentido = 0;
    Puntuacion punta;

    int punto[][] = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 0, 4, 0, 4, 0, 4, 0, 4, 0, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 0, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 0, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        //                                                                                                                                                     //                                                 
        {1, 2, 2, 2, 2, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 7, 8, 9, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 4, 0, 4, 0, 4, 0, 4, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 4, 0, 4, 0, 4, 0, 4, 0, 2, 2, 2, 1, 2, 2, 2, 2, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 4, 4, 5, 4, 4, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 3, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 3, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 0, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    //pacman
    int xPac = 27;
    int yPac = 49;

    //movimientos de los fantasmas
    Random rnd = new Random();

    //fantasma1 = 8
    int mov_fan1 = (int) (rnd.nextDouble() * 4 + 1);
    int xfan1 = 50;
    int yfan1 = 15;

    //fantasma2 = 7
    int mov_fan2 = (int) (rnd.nextDouble() * 4 + 1);
    int xfan2 = 49;
    int yfan2 = 15;

    //fantasma3 = 0
    int mov_fan3 = (int) (rnd.nextDouble() * 4 + 1);
    int xfan3 = 51;
    int yfan3 = 15;

    //perder
    boolean perder = false;
    //ganar
    boolean ganar = false;
    //poder
    boolean poder = false;

    //vueltas
    int vueltas = -1;
    //varible de poder
    int variable_poder = 0;
    //parpadeo
    boolean parpadeo = false;
    //num parpadeo
    int parpa = 0;

    public Graficos(Puntuacion pun) {
        kit = Toolkit.getDefaultToolkit();
        dimensionPantalla = kit.getScreenSize();
        altura = 100;
        anchura = 200;
        this.addGLEventListener(this);
        this.addKeyListener(this);
        this.punta = pun;
    }

    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void sentido(int valor) {
        this.sentido = valor;
    }

    public int getSentido() {
        return sentido;
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        punta.setPuntuacion(puntuacion);
        punta.Repinta();
        verificarGanar();
        System.out.println("variable poder " + variable_poder);
        if (poder) {
            System.out.println("poder true");
            if (variable_poder + 50 < vueltas) {
                variable_poder = 0;
                poder = false;
                parpadeo = false;
            } else {
                System.out.println("desaparece en " + ((vueltas - variable_poder) - 50) * -1);
            }
        } else {
            System.out.println("poder false");
        }
        if (ganar == false && perder == false) {
            System.out.println("Score " + puntuacion);
            Pintar(drawable.getGL());
            repaint();
            try {
                vueltas++;
                Thread.sleep(100);
                System.out.println("veces " + vueltas);
            } catch (Exception e) {
            }
        } else {
            if (ganar) {
                JOptionPane.showMessageDialog(null, "HAS GANADO");
            }
            if (perder) {
                JOptionPane.showMessageDialog(null, "HAS PERDIDO");
            }
        }
    }

    public void PowerPoints(int j, int i, GL gl) {
        //Puntos de Poder
        if (punto[j][i] == 3) {
            gl.glPointSize(10);
            gl.glColor3d(0, 1, 0);
            gl.glBegin(gl.GL_POINTS);
            for (double angul = 0.0f; angul < 2 * Math.PI; angul += 0.1) {
                double n = (double) ((double) .01 * Math.sin(angul));
                double m = (double) ((double) .01 * Math.cos(angul));
                gl.glVertex2d(n + i, m + j);
            }
            gl.glEnd();
        }
    }

    public void Pacman(int j, int i, GL gl) {
        if (punto[j][i] == 5) {
            //System.out.println("Pos" + "[" + i + " " + j + "]");
            gl.glPointSize(12);
            gl.glColor3f(1.0f, 1.0f, 0.0f);
            gl.glBegin(gl.GL_POLYGON);
            double angulo = 30.0;
            int nv = 30;
            double x2 = 0;
            double y2 = 0;
            double incremento = 2 * Math.PI / nv;
            for (int t = 0; t < nv + 1; t++) {
                double p1 = 2 * Math.cos(angulo);//ancho
                double p2 = 1.5 * Math.sin(angulo);//alto
                x2 = p1 + i;
                y2 = p2 + j;
                gl.glVertex2d(x2, y2);
                angulo += incremento;
            }
            gl.glEnd();
            gl.glPointSize(16);
            gl.glColor3d(0, 0, 0);
            gl.glBegin(gl.GL_POLYGON);
            gl.glVertex2d(i - .30, y2 + .17);//P1
            gl.glVertex2d(x2 - .10, y2 + 0.10);//P2
            gl.glVertex2d(i, j + 0.20);//P2
            gl.glEnd();

            switch (sentido) {
                //arriba
                case 1:
                    if (punto[j - 1][i] == 4 || punto[j - 1][i] == 0) {
                        if (punto[j - 1][i] == 0) {
                            puntuacion++;
                        }
                        punto[j - 1][i] = 5;
                        punto[j][i] = 4;
                    }
                    if (punto[j - 1][i] == 3) {
                        punto[j - 1][i] = 5;
                        punto[j][i] = 4;
                        variable_poder = vueltas;
                        poder = true;
                        parpadeo = true;
                        puntuacion += 5;
                        System.out.println("Comiste un poder");
                    }
                    if (punto[j - 1][i] == 7 || punto[j - 1][i] == 8 || punto[j - 1][i] == 9) {
                        if (poder) {
                            switch (punto[j - 1][i]) {
                                case 7:
                                    punto[15][50] = 7;
                                    punto[j - 1][i] = 0;
                                case 8:
                                    punto[15][50] = 8;
                                    punto[j - 1][i] = 0;
                                case 9:
                                    punto[15][50] = 9;
                                    punto[j - 1][i] = 0;
                            }
                            puntuacion += 30;
                        } else {
                            perder = true;
                        }
                    }
                    break;
                //derecha
                case 2:
                    if (punto[j][i + 1] == 4 || punto[j][i + 1] == 0) {
                        if (punto[j][i + 1] == 0) {
                            puntuacion++;
                        }
                        punto[j][i + 1] = 5;
                        punto[j][i] = 4;
                    }
                    if (punto[j][i + 1] == 3) {
                        punto[j][i + 1] = 5;
                        punto[j][i] = 4;
                        variable_poder = vueltas;
                        poder = true;
                        parpadeo = true;
                        puntuacion += 5;
                    }
                    if (punto[j][i + 1] == 7 || punto[j][i + 1] == 8 || punto[j][i + 1] == 9) {
                        if (poder) {
                            switch (punto[j][i + 1]) {
                                case 7:
                                    punto[15][50] = 7;
                                    punto[j][i + 1] = 0;
                                case 8:
                                    punto[15][50] = 8;
                                    punto[j][i + 1] = 0;
                                case 9:
                                    punto[15][50] = 9;
                                    punto[j][i + 1] = 0;
                            }
                            puntuacion += 30;
                        } else {
                            perder = true;
                        }
                    }
                    break;
                //abajo
                case 3:
                    if (punto[j + 1][i] == 4 || punto[j + 1][i] == 0) {
                        if (punto[j + 1][i] == 0) {
                            puntuacion++;
                        }
                        punto[j + 1][i] = 5;
                        punto[j][i] = 4;
                    }
                    if (punto[j + 1][i] == 3) {
                        punto[j + 1][i] = 5;
                        punto[j][i] = 4;
                        variable_poder = vueltas;
                        poder = true;
                        parpadeo = true;
                        puntuacion += 5;
                    }
                    if (punto[j + 1][i] == 7 || punto[j + 1][i] == 8 || punto[j + 1][i] == 9) {
                        if (poder) {
                            switch (punto[j + 1][i]) {
                                case 7:
                                    punto[15][50] = 7;
                                    punto[j + 1][i] = 0;
                                case 8:
                                    punto[15][50] = 8;
                                    punto[j + 1][i] = 0;
                                case 9:
                                    punto[15][50] = 9;
                                    punto[j + 1][i] = 0;
                            }
                            puntuacion += 30;
                        } else {
                            perder = true;
                        }
                    }
                    break;
                //izquierda
                case 4:
                    if (punto[j][i - 1] == 4 || punto[j][i - 1] == 0) {
                        if (punto[j][i - 1] == 0) {
                            puntuacion++;
                        }
                        punto[j][i - 1] = 5;
                        punto[j][i] = 4;
                    }
                    if (punto[j][i - 1] == 3) {
                        punto[j][i - 1] = 5;
                        punto[j][i] = 4;
                        variable_poder = vueltas;
                        poder = true;
                        parpadeo = true;
                        puntuacion += 5;
                    }
                    if (punto[j][i - 1] == 7 || punto[j][i - 1] == 8 || punto[j][i - 1] == 9) {
                        if (poder) {
                            switch (punto[j][i - 1]) {
                                case 7:
                                    punto[15][50] = 7;
                                    punto[j][i - 1] = 0;
                                case 8:
                                    punto[15][50] = 8;
                                    punto[j][i - 1] = 0;
                                case 9:
                                    punto[15][50] = 9;
                                    punto[j][i - 1] = 0;
                            }
                            puntuacion += 30;
                        } else {
                            perder = true;
                        }
                    }
                    break;
            }
        }
    }

    public void Points(int j, int i, GL gl) {
        if (punto[j][i] == 0) {
            gl.glPointSize(4);
            gl.glColor3d(1, 1, 1);
            gl.glBegin(gl.GL_POINTS);
            for (double angul = 0.0f; angul < 2 * Math.PI; angul += 0.1) {
                double n = (double) ((double) .01 * Math.sin(angul));
                double m = (double) ((double) .01 * Math.cos(angul));
                gl.glVertex2d(n + i, m + j);
            }
            gl.glEnd();
        }
    }

    public void Ghost1(int j, int i, GL gl) {
        //Fantasma1
        if (punto[j][i] == 8) {
            //System.out.println("Fantasma1 Pos" + "[" + i + " " + j + "]\n mov_fan1 = " + mov_fan1);
            gl.glPointSize(12);
            if (parpadeo) {
                if (parpa == 0) {
                    gl.glColor3f(1.0f, 0.0f, 0.0f);
                    parpa = 1;
                } else {
                    gl.glColor3f(0.0f, 0.0f, 1.0f);
                    parpa = 0;
                }
            } else {
                parpa = 0;
                gl.glColor3f(1.0f, 0.0f, 0.0f);
            }
            //gl.glPointSize(4);
            gl.glBegin(gl.GL_POLYGON);
            double angulo = 0.0;
            int nv = 20;
            double x2 = 0;
            double y2 = 0;
            double incremento = 1 * Math.PI / nv;
            for (int t = 0; t < nv + 1; t++) {
                double p1 = 2 * Math.cos(angulo);//ancho
                double p2 = -2 * Math.sin(angulo);//alto
                x2 = p1 + i;
                y2 = p2 + j+1;
                gl.glVertex2d(x2, y2);
                angulo += incremento;
            }
            gl.glEnd();
            gl.glPointSize(5);
            gl.glColor3d(0.0f, 1.0f, 0.0f);
            gl.glBegin(gl.GL_POINTS);
            gl.glVertex2d(x2+1,y2-1);
            gl.glBegin(gl.GL_POINTS);
            gl.glVertex2d(x2+2,y2-1);
            gl.glEnd();

            switch (mov_fan1) {
                //arriba
                case 1:
                    if (punto[j - 1][i] == 4 || punto[j - 1][i] == 0 || punto[j - 1][i] == 7 || punto[j - 1][i] == 9) {
                        int anterior = punto[j - 1][i];
                        punto[j - 1][i] = 8;
                        punto[j][i] = anterior;
                    }
                    if (punto[j - 1][i] == 3) {
                        int anterior = punto[j - 2][i];
                        punto[j - 2][i] = 8;
                        punto[j][i] = anterior;
                    }
                    if (punto[j - 1][i] == 2) {
                        mov_fan1 = (int) (rnd.nextDouble() * 4 + 1);
                    }
                    break;
                //derecha
                case 2:
                    if (punto[j][i + 1] == 4 || punto[j][i + 1] == 0 || punto[j][i + 1] == 7 || punto[j][i + 1] == 9) {
                        int anterior = punto[j][i + 1];
                        punto[j][i + 1] = 8;
                        punto[j][i] = anterior;
                    }
                    if (punto[j][i + 1] == 3) {
                        int anterior = punto[j][i + 2];
                        punto[j][i + 2] = 8;
                        punto[j][i] = anterior;
                    }
                    if (punto[j][i + 1] == 2) {
                        mov_fan1 = (int) (rnd.nextDouble() * 4 + 1);
                    }
                    break;
                //abajo
                case 3:
                    if (punto[j + 1][i] == 4 || punto[j + 1][i] == 0 || punto[j + 1][i] == 7 || punto[j + 1][i] == 9) {
                        int anterior = punto[j + 1][i];
                        punto[j + 1][i] = 8;
                        punto[j][i] = anterior;
                    }
                    if (punto[j + 1][i] == 3) {
                        int anterior = punto[j + 2][i];
                        punto[j + 2][i] = 8;
                        punto[j][i] = anterior;
                    }
                    if (punto[j + 1][i] == 2) {
                        mov_fan1 = (int) (rnd.nextDouble() * 4 + 1);
                    }
                    break;
                //izquierda
                case 4:
                    if (punto[j][i - 1] == 4 || punto[j][i - 1] == 0 || punto[j][i - 1] == 7 || punto[j][i - 1] == 9) {
                        int anterior = punto[j][i - 1];
                        punto[j][i - 1] = 8;
                        punto[j][i] = anterior;
                    }
                    if (punto[j][i - 1] == 3) {
                        int anterior = punto[j][i - 2];
                        punto[j][i - 2] = 8;
                        punto[j][i] = anterior;
                    }
                    if (punto[j][i - 1] == 2) {
                        mov_fan1 = (int) (rnd.nextDouble() * 4 + 1);
                    }
                    break;
            }
        }
    }

    public void Ghost2(int j, int i, GL gl) {
        //Fantasma2
        if (punto[j][i] == 7) {
            //System.out.println("Fantasma2 Pos" + "[" + i + " " + j + "]\n mov_fan2 = " + mov_fan2);
            gl.glPointSize(12);
            if (parpadeo) {
                if (parpa == 0) {
                    gl.glColor3f(1.0f, 0.0f, 0.0f);
                    parpa = 1;
                } else {
                    gl.glColor3f(0.0f, 0.0f, 1.0f);
                    parpa = 0;
                }
            } else {
                parpa = 0;
                gl.glColor3f(1.0f, 0.0f, 0.0f);
            }
            gl.glBegin(gl.GL_POLYGON);
            double angulo = 0.0;
            int nv = 20;
            double x2 = 0;
            double y2 = 0;
            double incremento = 1 * Math.PI / nv;
            for (int t = 0; t < nv + 1; t++) {
                double p1 = 2 * Math.cos(angulo);//ancho
                double p2 = -2 * Math.sin(angulo);//alto
                x2 = p1 + i;
                y2 = p2 + j+1;
                gl.glVertex2d(x2, y2);
                angulo += incremento;
            }
            gl.glEnd();
            gl.glPointSize(5);
            gl.glColor3d(0.0f, 1.0f, 0.0f);
            gl.glBegin(gl.GL_POINTS);
            gl.glVertex2d(x2+1,y2-1);
            gl.glBegin(gl.GL_POINTS);
            gl.glVertex2d(x2+2,y2-1);
            gl.glEnd();

            switch (mov_fan2) {
                //arriba
                case 1:
                    if (punto[j - 1][i] == 4 || punto[j - 1][i] == 0 || punto[j - 1][i] == 8 || punto[j - 1][i] == 9) {
                        int anterior = punto[j - 1][i];
                        punto[j - 1][i] = 7;
                        punto[j][i] = anterior;
                    }
                    if (punto[j - 1][i] == 3) {
                        int anterior = punto[j - 2][i];
                        punto[j - 2][i] = 7;
                        punto[j][i] = anterior;
                    }
                    if (punto[j - 1][i] == 2) {
                        mov_fan2 = (int) (rnd.nextDouble() * 4 + 1);
                    }
                    break;
                //derecha
                case 2:
                    if (punto[j][i + 1] == 4 || punto[j][i + 1] == 0 || punto[j][i + 1] == 8 || punto[j][i + 1] == 9) {
                        int anterior = punto[j][i + 1];
                        punto[j][i + 1] = 7;
                        punto[j][i] = anterior;
                    }
                    if (punto[j][i + 1] == 3) {
                        int anterior = punto[j][i + 2];
                        punto[j][i + 2] = 7;
                        punto[j][i] = anterior;
                    }
                    if (punto[j][i + 1] == 2) {
                        mov_fan2 = (int) (rnd.nextDouble() * 4 + 1);
                    }
                    break;
                //abajo
                case 3:
                    if (punto[j + 1][i] == 4 || punto[j + 1][i] == 0 || punto[j + 1][i] == 8 || punto[j + 1][i] == 9) {
                        int anterior = punto[j + 1][i];
                        punto[j + 1][i] = 7;
                        punto[j][i] = anterior;
                    }
                    if (punto[j + 1][i] == 3) {
                        int anterior = punto[j + 2][i];
                        punto[j + 2][i] = 7;
                        punto[j][i] = anterior;
                    }
                    if (punto[j + 1][i] == 2) {
                        mov_fan2 = (int) (rnd.nextDouble() * 4 + 1);
                    }
                    break;
                //izquierda
                case 4:
                    if (punto[j][i - 1] == 4 || punto[j][i - 1] == 0 || punto[j][i - 1] == 8 || punto[j][i - 1] == 9) {
                        int anterior = punto[j][i - 1];
                        punto[j][i - 1] = 7;
                        punto[j][i] = anterior;
                    }
                    if (punto[j][i - 1] == 3) {
                        int anterior = punto[j][i - 2];
                        punto[j][i - 2] = 7;
                        punto[j][i] = anterior;
                    }
                    if (punto[j][i - 1] == 2) {
                        mov_fan2 = (int) (rnd.nextDouble() * 4 + 1);
                    }
                    break;
            }
        }
    }

    public void Ghost3(int j, int i, GL gl) {
        //Fantasma3
        if (punto[j][i] == 9) {
            //System.out.println("Fantasma3 Pos" + "[" + i + " " + j + "]\n mov_fan3 = " + mov_fan3);
            gl.glPointSize(12);
            if (parpadeo) {
                if (parpa == 0) {
                    gl.glColor3f(1.0f, 0.0f, 0.0f);
                    parpa = 1;
                } else {
                    gl.glColor3f(0.0f, 0.0f, 1.0f);
                    parpa = 0;
                }
            } else {
                parpa = 0;
                gl.glColor3f(1.0f, 0.0f, 0.0f);
            }
            gl.glBegin(gl.GL_POLYGON);
            double angulo = 0.0;
            int nv = 20;
            double x2 = 0;
            double y2 = 0;
            double incremento = 1 * Math.PI / nv;
            for (int t = 0; t < nv + 1; t++) {
                double p1 = 2 * Math.cos(angulo);//ancho
                double p2 = -2 * Math.sin(angulo);//alto
                x2 = p1 + i;
                y2 = p2 + j+1;
                gl.glVertex2d(x2, y2);
                angulo += incremento;
            }
            gl.glEnd();
            gl.glPointSize(5);
            gl.glColor3d(0.0f, 1.0f, 0.0f);
            gl.glBegin(gl.GL_POINTS);
            gl.glVertex2d(x2+1,y2-1);
            gl.glBegin(gl.GL_POINTS);
            gl.glVertex2d(x2+2,y2-1);
            gl.glEnd();

            switch (mov_fan3) {
                //arriba
                case 1:
                    if (punto[j - 1][i] == 4 || punto[j - 1][i] == 0 || punto[j - 1][i] == 8 || punto[j - 1][i] == 7) {
                        int anterior = punto[j - 1][i];
                        punto[j - 1][i] = 9;
                        punto[j][i] = anterior;
                    }
                    if (punto[j - 1][i] == 3) {
                        int anterior = punto[j - 2][i];
                        punto[j - 2][i] = 9;
                        punto[j][i] = anterior;
                    }
                    if (punto[j - 1][i] == 2) {
                        mov_fan3 = (int) (rnd.nextDouble() * 4 + 1);
                    }
                    break;
                //derecha
                case 2:
                    if (punto[j][i + 1] == 4 || punto[j][i + 1] == 0 || punto[j][i + 1] == 8 || punto[j][i + 1] == 7) {
                        int anterior = punto[j][i + 1];
                        punto[j][i + 1] = 9;
                        punto[j][i] = anterior;
                    }
                    if (punto[j][i + 1] == 3) {
                        int anterior = punto[j][i + 2];
                        punto[j][i + 2] = 9;
                        punto[j][i] = anterior;
                    }
                    if (punto[j][i + 1] == 2) {
                        mov_fan3 = (int) (rnd.nextDouble() * 4 + 1);
                    }
                    break;
                //abajo
                case 3:
                    if (punto[j + 1][i] == 4 || punto[j + 1][i] == 0 || punto[j + 1][i] == 8 || punto[j + 1][i] == 7) {
                        int anterior = punto[j + 1][i];
                        punto[j + 1][i] = 9;
                        punto[j][i] = anterior;
                    }
                    if (punto[j + 1][i] == 3) {
                        int anterior = punto[j + 2][i];
                        punto[j + 2][i] = 9;
                        punto[j][i] = anterior;
                    }
                    if (punto[j + 1][i] == 2) {
                        mov_fan3 = (int) (rnd.nextDouble() * 4 + 1);
                    }
                    break;
                //izquierda
                case 4:
                    if (punto[j][i - 1] == 4 || punto[j][i - 1] == 0 || punto[j][i - 1] == 8 || punto[j][i - 1] == 7) {
                        int anterior = punto[j][i - 1];
                        punto[j][i - 1] = 9;
                        punto[j][i] = anterior;
                    }
                    if (punto[j][i - 1] == 3) {
                        int anterior = punto[j][i - 2];
                        punto[j][i - 2] = 9;
                        punto[j][i] = anterior;
                    }
                    if (punto[j][i - 1] == 2) {
                        mov_fan3 = (int) (rnd.nextDouble() * 4 + 1);
                    }
                    break;
            }
        }
    }

    public void fruta(int j, int i, GL gl) {
        //Fantasma3
        if (punto[j][i] == 8) {
            //System.out.println("Fantasma3 Pos" + "[" + i + " " + j + "]\n mov_fan3 = " + mov_fan3);
            gl.glPointSize(12);
            gl.glBegin(gl.GL_POLYGON);
            gl.glColor3f(1.0f, 0.0f, 1.0f);
            gl.glBegin(gl.GL_POLYGON);
            double angulo = 30.0;
            int nv = 30;
            double x2 = 0;
            double y2 = 0;
            double incremento = 2 * Math.PI / nv;
            for (int t = 0; t < nv + 1; t++) {
                double p1 = 2 * Math.cos(angulo);//ancho
                double p2 = 1.5 * Math.sin(angulo);//alto
                x2 = p1 + i;
                y2 = p2 + j;
                gl.glVertex2d(x2, y2);
                angulo += incremento;
            }
            gl.glEnd();
        }
    }
    public void Pintar(GL gl) {
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, 92, 47, 0, -5.0, 5.0);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        //codigo para crear el laberinto a base de la matriz
        for (int j = 0; j < punto.length; j++) {
            for (int i = 0; i < punto[0].length; i++) {
                //PARED
                if (punto[j][i] == 1) {
                    gl.glPointSize(16);
                    gl.glColor3f(1.0f, 0.4f, 0.8f);
                    gl.glBegin(gl.GL_POINTS);
                    gl.glVertex2d(i, j);
                    gl.glEnd();
                }
                //PACMAN
                Pacman(j, i, gl);
                //PUNTOS
                Points(j, i, gl);
                //Puntos de Poder
                PowerPoints(j, i, gl);
                //Fantasma1
                Ghost1(j, i, gl);
                //Fantasma2
                Ghost2(j, i, gl);
                //Fantasma3
                Ghost3(j, i, gl);

            }
        }
        gl.glFlush();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Arribba
        if (e.getKeyChar() == 'w') {
            sentido = 1;
        }
        //Derecha
        if (e.getKeyChar() == 'd') {
            sentido = 2;
        }
        //Abajo
        if (e.getKeyChar() == 's') {
            sentido = 3;
        }
        //Abajo
        if (e.getKeyChar() == 'a') {
            sentido = 4;
        }
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public boolean verificarGanar() {
        int cont = 0;
        for (int i = 0; i < punto.length; i++) {
            for (int j = 0; j < punto[0].length; j++) {
                if (punto[i][j] == 0) {
                    cont = 1;
                }
            }
        }
        if (cont == 0) {
            ganar = true;
        } else {
            ganar = false;
        }
        return ganar;
    }
}
