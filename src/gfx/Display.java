package gfx;

import java.awt.Canvas;

import javax.swing.JFrame;

public class Display {
	
	private String title;
	private JFrame jframe;
	private Canvas canvas;
	
	
	public Display(String Title) {
		title = Title;
		show();
	}
	
	private void show() {
		jframe = new JFrame(title);
		
		jframe.setResizable(false);
		jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jframe.setUndecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(jframe.getSize());
		canvas.setMaximumSize(jframe.getSize());
		canvas.setMinimumSize(jframe.getSize());
		
		jframe.add(canvas);
		jframe.pack();
		
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return jframe;
	}
	
}
