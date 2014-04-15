package com.broteam.tipe.testui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.*;

class Main{
 
  public static void main(String[] args){
      
      // pour ne pas bloquer l'UI, on utilise un mécanisme Swing qui ouvre la fenêtre dans un autre thread
      SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {
        	  setSystemLookAndFeel();
              Window w = new Window();
              w.setSize(800, 600);
              w.setVisible(true);
          }
      });
  }
  
  public static void setSystemLookAndFeel() {
      try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
              | UnsupportedLookAndFeelException e) {
          e.printStackTrace();
      }
 }
 

}