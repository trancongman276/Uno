package gameStates;
import java.awt.Graphics;

public abstract class State {
	private static State CurrentState = null;

	public static void setState(State St) {
		CurrentState = St;
	}

	public static State getState() {
		return CurrentState;
	}
	
	public State() {
	}
	public abstract void Update();

	public abstract void Render(Graphics g);
	
}
