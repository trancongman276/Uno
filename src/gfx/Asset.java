package gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Asset {
	public static List<BufferedImage> cardList;
	public static BufferedImage backCard;
	public static int w,h;

	public static void init() {
		cardList = new ArrayList<>();
		
		for(int i=0;i<=39;i++) {
			cardList.add(Resize.resize(ImageLoader.LoadImage("/Card/"+i+".png")));
		}
		
		backCard = Resize.resize(ImageLoader.LoadImage("/backCard.png"));
		
		w = (int) (cardList.get(0).getWidth()*Resize.x);
		h = (int) (cardList.get(0).getHeight()*Resize.y);
	}
}
