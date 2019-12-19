package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import gameStates.Game;
import gfx.Animation;
import gfx.Asset;
import input.MouseManager;
import main.DrawGame;

public class Card {
	
	private int val, x,y,index;
	private int newX, newY;
	private Type type;
	private Rectangle bound;
	private MouseManager mouse;
	
	private boolean holvering = false, played=false;
	
	public Card(int x, int y,int val, Type type) {
		this.x = x;
		newX = x;
		newY = y;
		this.y = y;
		this.val = val;
		this.type = type;
		this.mouse = DrawGame.getMouse();
		bound = new Rectangle(x,y,Asset.w,Asset.h);
		initIndex();
	}
	
	public void updatePlayer() {
//		soft();
		
		if(!played) {
			
			if(bound.contains(mouse.getX(),mouse.getY())) {
				holvering = true; // make hovering card look clearer 
				System.out.println("Player Card");
				if(mouse.isDoubleClick()) {
					Game.checkValid(this);
					mouse.setDoubleClick(false);
				}
			}else holvering = false;
		}
	}
	
	public void updateBot() {
//		soft();
		if(bound.contains(mouse.getX(),mouse.getY())) {
			System.out.println("Bot Card");}
		if(Game.delayBot != Game.botDelayTime)
			Game.delayBot++;
		if(Game.lastCard == null) Game.checkValid(this);
	}
	
	
	public void updateDraw() {
		if(bound.contains(mouse.getX(),mouse.getY())) {
			
			System.out.println("Draw Card");
			
			if(mouse.isDoubleClick() && Game.moveCard==null) {
				Game.moveCard = this;
				mouse.setDoubleClick(false);
			}
		}
	}
	
	public void renderPlayer(Graphics g) {
		x = Animation.move(x, newX, Game.cardSpeed);
		y = Animation.move(y, newY, Game.cardSpeed);
		g.drawImage(Asset.cardList.get(index),x,y,null);
		g.drawRect(x,y,Asset.w,Asset.h);
	}
	
	public void renderBot(Graphics g) {
		x = Animation.move(x, newX, Game.cardSpeed);
		y = Animation.move(y, newY, Game.cardSpeed);
		g.drawImage(Asset.backCard,x,y,null);
		g.drawLine(360, 360, 360+Game.delayBot, 360);
	}
	
	public void play() {
		newX=Game.playedPosX + Game.rnd.nextInt(Game.playedCardDistance);
		newY=Game.playedPosY + Game.rnd.nextInt(Game.playedCardDistance);
		played = true;
		Game.turn++;
	}
	
	private void soft() {
//		this.setY(Game.startPosPlayerY);
//				+Game.cardDistance*Game.playerCard.indexOf(this));
	}
	
	public void initIndex() {
		this.index = val;
		switch(type) {
		case RED:
			this.index +=0;
			break;
		case BLUE:
			this.index +=10;
			break;
		case GREEN:
			this.index +=20;
			break;
		case YELLOW:
			this.index +=30;
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
		newX = x;
		bound.setBounds(x,y,Asset.w,Asset.h);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		newY = y;
		bound.setBounds(x,y,Asset.w,Asset.h);
	}
	
	
}
