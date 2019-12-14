package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import gameStates.Game;
import gfx.Asset;
import input.MouseManager;
import main.DrawGame;

public class Card {
	
	private int val, x,y,index;
	private Type type;
	private Rectangle bound;
	private MouseManager mouse;
	
	private boolean holvering = false, played=false;
	
	public Card(int x, int y,int val, Type type) {
		this.x = x;
		this.y = y;
		this.val = val;
		this.type = type;
		this.mouse = DrawGame.getMouse();
		bound = new Rectangle(0,0,Asset.w,Asset.h);
	}
	
	public void updatePlayer() {
		if(!played) {
			if(bound.contains(mouse.getX(),mouse.getY())) {
				holvering = true; // make hovering card look clearer 
				if(mouse.isDoubleClick()) {
					Game.checkValid(this);
				}
			}else holvering = false;
		}
	}
	
	public void updateBot() {
		if(!played) {
			Game.checkValid(this);
		}
	}
	
	public void renderPlayer(Graphics g) {
		g.drawImage(Asset.cardList.get(index),x,y,null);
	}
	
	public void renderBot(Graphics g) {
		g.drawImage(Asset.backCard,x,y,null);
	}
	
	public void play() {
		x=Game.playedPosX + Game.rnd.nextInt(Game.playedCardDistance);
		y=Game.playedPosY + Game.rnd.nextInt(Game.playedCardDistance);
		played = true;
		Game.turn++;
	}
	
	public void initIndex() {
		this.index = val;
		switch(type) {
		case RED:
			this.index +=10;
			break;
		case YELLOW:
			this.index +=20;
			break;
		case BLUE:
			this.index +=30;
			break;
		case GREEN:
			this.index +=40;
			break;
		}
	}
	
	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public boolean isPlayed() {
		return played;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
