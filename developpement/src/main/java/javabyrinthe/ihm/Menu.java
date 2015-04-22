package javabyrinthe.ihm;

import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics; 
import org.newdawn.slick.SlickException; 
import org.newdawn.slick.state.BasicGameState; 
import org.newdawn.slick.state.StateBasedGame; 
import org.newdawn.slick.Input;

public class Menu extends BasicGameState{
	private static final int ID = 1; 
	private GameContainer container;

 	@Override
 	public int getID() {
 		return ID;
 	} 

 	public void keyReleased(int k,char c){
 		switch(k){
 			case (Input.KEY_SPACE):
 				container.exit();
 				break;
 			default:
 				break;
 		}
 	}
 
 	@Override
 	public void init(GameContainer container, StateBasedGame game) throws SlickException{
 		this.container = container;
 	}

 	@Override
 	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
 		g.drawString("le menu",100,50);
 	}

 	@Override
 	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {} 
}