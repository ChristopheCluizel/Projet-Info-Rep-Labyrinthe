package javabyrinthe.ihm;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class IHM extends StateBasedGame{
    private AppGameContainer container;
    private String mode, pseudo, action;
    private int nbJoueurMax;

	public IHM() {
        super("Javabyrinthe");
        mode = "";
        pseudo = "";
        action = "";
        nbJoueurMax = 0;
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException{
        addState(new Menu());
        addState(new GameWindow());
    }

    public static void main(String[] args) throws SlickException{
        try{
            AppGameContainer container = new AppGameContainer(new IHM());
            container.setDisplayMode(600,600,false);
            container.setTargetFrameRate(60);
            container.setShowFPS(false);
            container.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getMode(){ return this.mode; }
    public String getPseudo(){ return this.pseudo; }
    public String getAction(){ return this.action; }
    public int getNbJoueurMax(){ return this.nbJoueurMax; }
    public void setMode(String str){ this.mode=str; }
    public void setPseudo(String str){ this.pseudo=str; }
    public void setAction(String str){ this.action=str; }
    public void setNbJoueurMax(int i){ this.nbJoueurMax=i; }
}