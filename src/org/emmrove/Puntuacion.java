/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emmrove;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
/**
 *
 * @author emmrove
 */
public class Puntuacion extends JPanel {

    Color colorRosa = new Color(1.0f, 0.4f, 0.8f);
    Font f;
    int puntuacion = 0;

    public Puntuacion() {
        this.setBackground(Color.BLACK);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(colorRosa);
        g.fillRect(0, 0, 200, 8);
        g.fillRect(0, 530, 200, 16);
        g.fillRect(190, 0, 8, 530);

        g.setColor(Color.RED);
        f = new Font("", Font.BOLD, 30);
        g.setFont(f);
        g.drawString("SCORE", 50, 90);

        g.setColor(Color.WHITE);
        f = new Font("", Font.BOLD, 30);
        g.setFont(f);
        g.drawString("" + puntuacion, 85, 140);
    }

    public void setPuntuacion(int pun) {
        puntuacion = pun;
    }

    public int getPuntuacion() {
        return puntuacion;
    }
    
    public void Repinta(){
        this.repaint();
    }
}
