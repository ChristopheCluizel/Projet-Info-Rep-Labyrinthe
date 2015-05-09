package javabyrinthe.ihm;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics; 
import org.newdawn.slick.SlickException; 
import org.newdawn.slick.state.BasicGameState; 
import org.newdawn.slick.state.StateBasedGame; 
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.TextField;

import javabyrinthe.tools.Coordinate;

public class Menu extends BasicGameState{
	private static final int ID = 1; 
	private GameContainer container;
	private IHM game;
	private Button ia, manuel, go1, go2;
	private TextField pseudo, nbJoueur, code;

 	@Override
 	public int getID() {
 		return ID;
 	} 

 	@Override
 	public void mousePressed(int button, int x, int y){
 		//System.out.println("("+x+","+y+")");
 		if(ia.isClicked(x,y)){
 			if(ia.getSelected())
 				manuel.setSelected(false);
 		}
 		if(manuel.isClicked(x,y)){
 			if(manuel.getSelected())
 				ia.setSelected(false);
 		}
 		if(go1.isClicked(x,y)){
 			if(!pseudo.getText().isEmpty()){
 				if(ia.getSelected())
 					game.setMode("IA");
 				else
 					game.setMode("humain");
 				game.setPseudo(pseudo.getText());
 				game.setNbJoueurMax(0);
 				game.setAction("joindre");
 				game.enterState(2);
 			}
 		}
 		if(go2.isClicked(x,y)){
 			if(verifierChamps()){
 				if(ia.getSelected())
 					game.setMode("IA");
 				else
 					game.setMode("humain");
 				game.setPseudo(pseudo.getText());
 				game.setNbJoueurMax(Integer.parseInt(nbJoueur.getText()));
 				game.setAction("creer");
 				game.enterState(2);
 				System.out.println("State 2 entered");
 			}
 		}
 	}

 	@Override
 	public void keyReleased(int k,char c){
 		switch(k){
 			case (Input.KEY_ESCAPE):
 				container.exit();
 				break;
 			default:
 				break;
 		}
 	}

 	@Override
 	public void enter(GameContainer container, StateBasedGame game){
		go1.setSelected(false);
		go2.setSelected(false);
 	}
 
 	@Override
 	public void init(GameContainer container, StateBasedGame game) throws SlickException{
 		this.container = container;
 		this.game = (IHM)game;
 		ia = new Button(new Coordinate(350,170),60,20,"IA",false);
 		manuel = new Button(new Coordinate(430,170),60,20,"Manuel",true);
 		pseudo = new TextField(container,container.getDefaultFont(),350,140,150,20);
 		go1 = new Button(new Coordinate(350,280),40,20,"GO !",false);
 		go2 = new Button(new Coordinate(350,380),40,20,"GO !",false);
 		nbJoueur = new TextField(container,container.getDefaultFont(),390,350,20,20);
 		code = new TextField(container,container.getDefaultFont(),100,50,100,400);
 	}

 	@Override
 	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
 		g.drawImage(new Image("src/main/resources/backgournd.png"),0,0);
 		g.setColor(Color.white);
 		g.drawString("Accueil",252,10);
 		g.drawString("Javabyrinthe",365,65);
 		g.drawString("Règles du jeu :",260,110);
 		g.drawString("Pseudo :",260,140);
 		pseudo.render(container,g);
 		g.setColor(Color.white);
 		g.drawString("Mode :",260,170);
 		ia.afficher(g);
 		manuel.afficher(g);
 		g.setColor(Color.white);
 		g.drawString("Joindre une partie",260,250);
 		go1.afficher(g);
 		g.setColor(Color.white);
 		g.drawString("Créer une partie",260,320);
 		g.drawString("Nb joueurs :",270,350);
 		nbJoueur.render(container,g);
 		go2.afficher(g);
 	}

 	@Override
 	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {}

 	private boolean verifierChamps(){
 		String name = pseudo.getText();
 		if(name.isEmpty())
 			return false;
 		String nb = nbJoueur.getText();
 		if(!nb.matches("[0-4]"))
 			return false;
 		return true;
 	}
}