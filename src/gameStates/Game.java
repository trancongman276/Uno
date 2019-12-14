package gameStates;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import objects.Card;
import objects.Type;

public class Game extends State{
	
	public static final int nbStartCards = 5, nbCards = 36,
							startPosPlayerX = 0, startPosPlayerY = 0,
							startBotPlayerX = 0, startBotPlayerY = 0 ,cardDistance = 0,
							playedPosX = 0, playedPosY = 0, playedCardDistance = 0;
	
	private static List<Card> playerCard,botCard,drawCard,playedCard;

	public static Random rnd;
	public static int turn=1;
	
	public Game() {
		boolean[][] card = new boolean[10][4];
		int val=0,typenb=0;
		Type type;
		
		rnd = new Random();
		playerCard = new ArrayList<>();
		botCard = new ArrayList<>();
		drawCard = new ArrayList<>();
		playedCard = new ArrayList<>();
		
		for(int i=0;i<nbCards;i++) {
			while(!card[val][typenb]) {
				val = rnd.nextInt(10);
				typenb = rnd.nextInt(4);
				card[val][typenb] = false;
			}
			type = Type.values()[typenb];
			drawCard.add(new Card(0,0,val,type));
		}
		
		for(int i=0;i<nbStartCards;i++) {
			drawCard.get(0).setX(startPosPlayerX+cardDistance*i);
			drawCard.get(0).setY(startPosPlayerY+cardDistance*i);
			playerCard.add(drawCard.get(0));
			drawCard.remove(0);
			
			drawCard.get(0).setX(startPosPlayerX+cardDistance*i);
			drawCard.get(0).setY(startPosPlayerY+cardDistance*i);
			botCard.add(drawCard.get(0)); 
			drawCard.remove(0);
		}
	}
	public static void checkValid(Card card) {
		Card topCard = playedCard.get(playedCard.size()-1);
		if( (topCard.getType() == card.getType())
				|| (topCard.getVal()==card.getVal()) ) {
			playedCard.add(card);
			playerCard.remove(playerCard.indexOf(card));
			playedCard.get(playedCard.size()-1).play();
		}
	}
	
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		if(turn == 1) playerCard.forEach(card -> card.updatePlayer());
		else{		
			botCard.forEach(card -> card.updateBot());
			turn = 1;
		}
	}

	@Override
	public void Render(Graphics g) {
		// TODO Auto-generated method stub
		playerCard.forEach(card -> card.renderPlayer(g));
		botCard.forEach(card -> card.renderBot(g));
	}

}
