package javabyrinthe.ihm;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import javabyrinthe.tools.Coordinate;

public class Button{
	private Coordinate coord;
	private int height;
	private int width;
	private String text;
	private boolean selected;

	public Button(Coordinate c, int height, int width, String text, boolean selected){
		this.coord = c;
		this.height = height;
		this.width = width;
		this.text = text;
		this.selected = selected;
	}

	public boolean isClicked(int x, int y){
		if(x>coord.getX() && x<(coord.getX()+height) && y>coord.getY() && y<(coord.getY()+width)){
			selected = !selected;
			return true;
		}else{
			return false;
		}
	}

	public void afficher(Graphics g){
		if(selected)
			g.setColor(new Color(46, 204, 113));
		else
			g.setColor(new Color(236, 240, 241));
		g.fillRect(this.getX(),this.getY(),this.getHeight(),this.getWidth());
		g.setColor(new Color(44, 62, 80));
		g.drawString(text,this.getX(),this.getY());
	}

	public int getX(){ return this.coord.getX(); }
	public int getY(){ return this.coord.getY(); }
	public int getHeight(){ return this.height; }
	public int getWidth(){ return this.width; }
	public String getText(){ return this.text; }
	public boolean getSelected(){ return this.selected; }
	public void setSelected(boolean b){ this.selected=b; }

	public String toString(){ return this.text+"("+this.getX()+","+this.getY()+")";}
}