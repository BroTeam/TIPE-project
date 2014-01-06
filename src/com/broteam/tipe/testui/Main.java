package com.broteam.tipe.testui;

import javax.swing.SwingUtilities;

class Main{
 
  public static void main(String[] args){
      
      // pour ne pas bloquer l'UI, on utilise un mécanisme Swing qui ouvre la fenêtre dans un autre thread
      SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {
              Window w = new Window();
              w.setSize(500, 300);
              w.setVisible(true);
          }
      });
      
  }
 
}