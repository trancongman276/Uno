package gfx;

import java.awt.image.BufferedImage;

public class Animation {
	public static void gif(BufferedImage img, int x) {
		
	}
	
	public static int move(int i, int f, int speed) {
		if(i>f) {
			int t = i;
			i = f;
			f = t;
		}
		for(int k = 1;k<=speed;k++) {
			if(i!=f) i++;
		}
		return i;
	}
}
