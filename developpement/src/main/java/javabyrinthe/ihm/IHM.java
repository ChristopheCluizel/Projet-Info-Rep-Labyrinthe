package javabyrinthe.ihm;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class IHM extends StateBasedGame{
	private Menu jeu;
    private AppGameContainer container;

	public IHM() {
        super("This is a test");
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException{
        addState(new Menu());
    }

    public static void main(String[] args) throws SlickException{
        try{
            AppGameContainer container = new AppGameContainer(new IHM());
            container.setDisplayMode(600,600,false);
            container.setTargetFrameRate(60);
            container.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}