package gl;

import org.lwjgl.input.Mouse;

public class MouseUtil {
	public void printCoords() {
		if(Mouse.isButtonDown(0)) {
			int x = Mouse.getX();
			int y = Mouse.getY();
			System.out.println("mouse down at: " + x + ", " + y);
		}
	}
}
