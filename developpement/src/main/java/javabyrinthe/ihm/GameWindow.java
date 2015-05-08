package javabyrinthe.ihm;

import java.io.FileNotFoundException;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.UUID;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics; 
import org.newdawn.slick.SlickException; 
import org.newdawn.slick.state.BasicGameState; 
import org.newdawn.slick.state.StateBasedGame; 
import org.newdawn.slick.Input;

import javabyrinthe.labyrinthe.Labyrinth;
import javabyrinthe.labyrinthe.LabyrinthGenerator;

import graph.Graph;

import javabyrinthe.tools.Coordinate;

import javabyrinthe.jeu.JoueurHumain;
import javabyrinthe.jeu.JoueurIA;
import javabyrinthe.jeu.JoueurInterface;
import javabyrinthe.jeu.Partie;
import javabyrinthe.jeu.PartieManager;

public class GameWindow extends BasicGameState{
	private static final int ID = 2; 
	private GameContainer container;
	private IHM game;
	private int tailleCase, tailleMur, offset;
	private Labyrinth l;
	private JoueurInterface stub;
	private String selection;

 	@Override
 	public int getID() {
 		return ID;
 	} 

 	public void keyReleased(int k,char c){
 		switch(k){
 			case (Input.KEY_ESCAPE):
 				container.exit();
 				break;
 			case (Input.KEY_UP):
 				selection = "HAUT";
 			break;
 			case (Input.KEY_DOWN):
 				selection = "BAS";
 			break;
 			case (Input.KEY_RIGHT):
 				selection = "DROITE";
 			break;
 			case (Input.KEY_LEFT):
 				selection = "GAUCHE";
 			break;
 			default:
 				break;
 		}
 	}
 
 	@Override
 	public void enter(GameContainer container, StateBasedGame game){
 		if(this.game.getAction().equals("joindre"))
 			rejoindrePartie();
 		else if(this.game.getAction().equals("creer"))
 			creerPartie();
 	}

 	@Override
 	public void init(GameContainer container, StateBasedGame game) throws SlickException{
 		this.container = container;
 		this.game=(IHM)game;
 		this.selection = "";
 		tailleCase=50;
 		tailleMur=5;
 		offset=50;
 		LabyrinthGenerator lg = new LabyrinthGenerator();
 		try{
 			l = lg.loadLabyrinth("src/main/resources/maze1.dot");
 		}catch(FileNotFoundException e){
 			e.printStackTrace();
 		}
 	}

 	@Override
 	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
 		//affichage du labyrinthe
 		int nbCases = l.getSize()*l.getSize();
 		LabyrinthGenerator lg = new LabyrinthGenerator();
 		g.setColor(Color.white);
 		for(int i=0 ; i<l.getSize() ; i++){
 			Coordinate c = lg.keyToCoordinates(i,l.getWidth());
 			g.fillRect(offset + c.getX()*tailleCase + tailleMur, offset + c.getY()*tailleCase +tailleMur,tailleCase-tailleMur,tailleCase-tailleMur);
 		}

 		for(int i=0 ; i<l.getSize() ; i++){
 			Coordinate c = lg.keyToCoordinates(i,l.getWidth());
 			Coordinate cp = new Coordinate(c.getX()+1,c.getY());
 			if(l.isAuthorizedMove(c,cp))
 				g.fillRect(offset+cp.getX()*tailleCase,offset+cp.getY()*tailleCase+tailleMur,tailleMur,tailleCase-tailleMur);
 			cp = new Coordinate(c.getX(),c.getY()+1);
 			if(l.isAuthorizedMove(c,cp))
 				g.fillRect(offset+cp.getX()*tailleCase+tailleMur,offset+cp.getY()*tailleCase,tailleCase-tailleMur,tailleMur);
 		}

 		Coordinate depart = l.getDeparture();
 		Coordinate arrive = l.getArrival();
 		g.setColor(Color.green);
 		g.fillRect(offset + depart.getX()*tailleCase + tailleMur, offset + depart.getY()*tailleCase +tailleMur,tailleCase-tailleMur,tailleCase-tailleMur);
 		g.setColor(Color.red);
 		g.fillRect(offset + arrive.getX()*tailleCase + tailleMur, offset + arrive.getY()*tailleCase +tailleMur,tailleCase-tailleMur,tailleCase-tailleMur);
 		
 		//selection
 		try{
 			g.setColor(Color.white);
 			if(stub.getTour()){
  				g.drawString("C'est à votre tour !",300,420);
 				g.drawString("Deplacement : "+selection,300,450);
 			}else{
 				g.drawString("Patientez...",300,420);
 			}

 			//affichage des joueurs
 			ArrayList<JoueurInterface> joueurs = stub.getPartie().getJoueurs();
 			g.setColor(Color.white);
 			for(int i=0 ; i<joueurs.size() ; i++){
 				g.drawString(joueurs.get(i).getActualPosition().toString(),100,450+i*20);
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 	}

 	@Override
 	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {} 

 	public void setSelection(String str){
 		this.selection = str;
 	}

 	public String getSelection(){
 		return this.selection;
 	}

 	private void creerPartie(){
 		String machine = "localhost";
		int port = 1099;
		int nbJoueurMaxParPartie = this.game.getNbJoueurMax();

		try {
			// création du joueur
			if (game.getMode().equals("humain")) {
				stub = (JoueurInterface)UnicastRemoteObject.exportObject(new JoueurHumain(game.getPseudo()), 0);
			} else {
				stub = (JoueurInterface) UnicastRemoteObject.exportObject(new JoueurIA(game.getPseudo()), 0);
			}

			// ajout du joueur au registre
			Registry registry = LocateRegistry.getRegistry(machine, port);
			String stringIdClient = UUID.randomUUID().toString();
			if (!Arrays.asList(registry.list()).contains(stringIdClient)) {
				registry.bind(stringIdClient, stub);
			} else {
				registry.rebind(stringIdClient, stub);
			}
			System.out.println("Service " + stringIdClient + " lié au registre");

			// recupération du partie manager et entrée dans une parite
			PartieManager partieManager = (PartieManager) registry.lookup("PartieManager");
			Partie partie;

			if (game.getMode().equals("humain")) {
				partie = partieManager.creerPartie(stringIdClient, game.getPseudo(), "humain", nbJoueurMaxParPartie);
			} else {
				partie = partieManager.creerPartie(stringIdClient, game.getPseudo(), "IA", nbJoueurMaxParPartie);
			}
			// registry.unbind(stringIdClient);
		} catch (Exception e) {
			System.out.println("Client exception: " + e);
		}
 	}

 	private void rejoindrePartie(){
 		String machine = "localhost";
		int port = 1099;

		try {
			// création du joueur
			if (game.getMode().equals("humain")) {
				stub = (JoueurInterface)UnicastRemoteObject.exportObject(new JoueurHumain(game.getPseudo()), 0);
			} else {
				stub = (JoueurInterface) UnicastRemoteObject.exportObject(new JoueurIA(game.getPseudo()), 0);
			}

			// ajout du joueur au registre
			Registry registry = LocateRegistry.getRegistry(machine, port);
			String stringIdClient = UUID.randomUUID().toString();
			if (!Arrays.asList(registry.list()).contains(stringIdClient)) {
				registry.bind(stringIdClient, stub);
			} else {
				registry.rebind(stringIdClient, stub);
			}
			System.out.println("Service " + stringIdClient + " lié au registre");

			// recupération du partie manager et entrée dans une parite
			PartieManager partieManager = (PartieManager) registry.lookup("PartieManager");
			Partie partie;

			if (game.getMode().equals("humain")) {
				partie = partieManager.rejoindrePartie(stringIdClient, game.getPseudo(), "humain");
			} else {
				partie = partieManager.rejoindrePartie(stringIdClient, game.getPseudo(), "IA");
			}
			// registry.unbind(stringIdClient);
		} catch (Exception e) {
			System.out.println("Client exception: " + e);
			game.enterState(1);
		}
 	}
}