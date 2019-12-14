package gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Asset {
	public static List<BufferedImage> cardList;
	public static BufferedImage backCard;
	public static int w,h;

	public static void init() {
		
		w = cardList.get(0).getWidth();
		h = cardList.get(0).getHeight();
	}
}
