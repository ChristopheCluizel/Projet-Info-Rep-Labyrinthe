package javabyrinthe.jeu;

import java.lang.*;

import javabyrinthe.*;

public class PartieRunner implements  Runnable {
  Partie partie;
  public PartieRunner(Partie partie){
    this.partie = partie;
  }
  public void run(){
    while(partie.getnbTour() < partie.getnbTourMax()){
    	System.out.println();
      System.out.println("Partie "+ partie + " en cours. Etat nombre de tours : "+ partie.getnbTour() + "/" + partie.getnbTourMax());
      try{
          partie.tourSuivant();
        }catch(Exception e){
          System.out.println("/!\\PartieRunner : " + e);
        }
    }
    System.out.println("Fin de partie" + partie);
  }
}
