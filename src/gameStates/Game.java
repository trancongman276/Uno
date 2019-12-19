package gameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import gfx.Asset;
import objects.Card;
import objects.Type;

public class Game extends State{
	
	public static final int nbStartCards = 5, nbCards = 40, cardSpeed = 8, botDelayTime = 200,							startPosPlayerX = 340, startPosPlayerY = 630,
							startPosBotX = 340, startPosBotY = 10 ,cardDistance = Asset.w,
							playedPosX = 690, playedPosY = 317, playedCardDistance = 10,
							drawCardPosX = 310, drawCardPosY = 320;
	
	public static List<Card> playerCard,botCard,drawCard,playedCard;
	public static Card lastCard;

	public static Card moveCard;
	public static Random rnd;
	public static int turn=1;
	public static int delayBot = 0;
	
	private static boolean checkFinalCard = false;
	
	public Game() {
		rnd = new Random();
		playerCard = new ArrayList<>();
		botCard = new ArrayList<>();
		drawCard = new ArrayList<>();
		playedCard = new ArrayList<>();
		
		for(int i=0;i<nbCards;i++) {
			if(i<10)
				drawCard.add(new Card(drawCardPosX,drawCardPosY,
						i,Type.values()[(int) (i/10)]));
			else 
				drawCard.add(new Card(drawCardPosX,drawCardPosY,
						i%10,Type.values()[(int) (i/10)]));
		}
		
		Collections.shuffle(drawCard);
		
		for(int i=0;i<nbStartCards;i++) {
			playerCard.add(drawCard.get(0));drawCard.remove(0);
			playerCard.get(playerCard.size()-1).setX(startPosPlayerX+cardDistance*i);
			playerCard.get(playerCard.size()-1).setY(startPosPlayerY);
			
			botCard.add(drawCard.get(0)); drawCard.remove(0);
			botCard.get(botCard.size()-1).setX(startPosBotX+cardDistance*i);
			botCard.get(botCard.size()-1).setY(startPosBotY);
		}
	}
	public static void checkValid(Card card) {
		if(playedCard.size()!=0) {
			Card topCard = playedCard.get(playedCard.size()-1);
			if( (topCard.getType() == card.getType())
					|| (topCard.getVal()==card.getVal()) ) {
				lastCard = card;
//				card.play();
			}
		}else {
			lastCard = card;
//			card.play();
		}
		checkFinalCard =true;
	}
	
	@Override
	public void Update() {
		soft();
		// TODO Auto-generated method stub
		drawCard.forEach(card->card.updateDraw());
		if(turn == 1) 
		{
			checkFinalCard = false;
			for(int i=0;i<playerCard.size();i++) {
				playerCard.get(i).updatePlayer();
				if(lastCard!=null) {
					playedCard.add(lastCard);
					playerCard.get(playerCard.indexOf(lastCard)).play();
					playerCard.remove(playerCard.indexOf(lastCard));
					lastCard = null;
					break;
				}
				if(moveCard!=null) {
					drawCard.get(0).setX(startPosPlayerX+cardDistance*(playerCard.size()-1));
					drawCard.get(0).setY(startPosPlayerY);
					playerCard.add(moveCard);
					drawCard.remove(0);

					moveCard = null;
					System.out.println("pass");
					turn++;
					break;
				}
			}
		}
		else{		
			for(int i=0;i<botCard.size();i++) {
				botCard.get(i).updateBot();}
			if(delayBot == botDelayTime) {
				if(lastCard!=null) {
					playedCard.add(lastCard);
					botCard.get(botCard.indexOf(lastCard)).play();
					botCard.remove(botCard.indexOf(lastCard));
					lastCard = null;
					turn = 1;
				}
				if(turn!=1) {
					botCard.add(drawCard.get(0)); drawCard.remove(0);
					botCard.get(botCard.size()-1).setX(startPosBotX+cardDistance*(botCard.size()-1));
					botCard.get(botCard.size()-1).setY(startPosBotY);
					turn = 1;
				}
				delayBot=0;
			}
		}
	}

	@Override
	public void Render(Graphics g) {
		// TODO Auto-generated method stub
		playerCard.forEach(card -> card.renderPlayer(g));
		botCard.forEach(card -> card.renderPlayer(g));
		drawCard.forEach(card -> card.renderBot(g));
		playedCard.forEach(card -> card.renderPlayer(g));
		g.setColor(new Color(255,0,0));
		if(turn ==1 )
			g.drawString("Player turn", 100, 100);
		else g.drawString("Bot turn", 100, 100);
	}
	
	public void soft() {
		playerCard.forEach(card -> {
			card.setX(startPosPlayerX+playerCard.indexOf(card)*cardDistance);
		});
		
		botCard.forEach(card -> {
			card.setX(startPosBotX+botCard.indexOf(card)*cardDistance);
		});
	}
}
