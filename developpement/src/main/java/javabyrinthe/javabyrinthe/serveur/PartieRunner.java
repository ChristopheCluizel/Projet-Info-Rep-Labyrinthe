package javabyrinthe.serveur;

import java.lang.*;
import javabyrinthe.*;

public class PartieRunner implements  Runnable {
  Partie partie;
  public PartieRunner(Partie partie){
    this.partie = partie;
  }
  public void run(){
    while(partie.getnbTour() < partie.getnbTourMax()){
      System.out.println("partie"+partie + " "+ partie.getnbTour() +"/"+partie.getnbTourMax());
      try{
          partie.tourSuivant();
        }catch(Exception e){
          System.out.println("/!\\PartieRunner : " + e);
        }
    }
    System.out.println("Partie Fini" + partie);
  }
}
