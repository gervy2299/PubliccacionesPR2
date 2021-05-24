/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

/**
 *
 * @author eltu_
 */
public class clases {

    public void soloMayuscula(JTextField txt, JTextField txt2) {
        txt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {
                char c = arg0.getKeyChar();
                String cad = ("" + c).toUpperCase();
                c = cad.charAt(0);
                arg0.setKeyChar(c);

                if (Character.isDigit(c) == false) {
                } else {
                    arg0.consume();
                }

            }

            @Override
            public void keyPressed(KeyEvent arg0) {
                if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
                    txt2.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent arg0) {

            }
        });
    }

    public void mayusculaNumber(JTextField txt) {
        txt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {
                char c = arg0.getKeyChar();
                String cad = ("" + c).toUpperCase();
                c = cad.charAt(0);
                arg0.setKeyChar(c);

//                if (Character.isDigit(c) == false) {
//                }else{
//                    arg0.consume();
//                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
    public void soloNumber(JTextField txt) {
        txt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {
                char c = arg0.getKeyChar();
                String cad = ("" + c).toUpperCase();
                c = cad.charAt(0);
                arg0.setKeyChar(c);

                if (Character.isDigit(c) == false) {
                    arg0.consume();
                }else{
                    
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
