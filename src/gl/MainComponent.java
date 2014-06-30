package gl;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class MainComponent {
	public MainComponent() {
		initDisplay();
		mainLoop();
	}
	
	private void initDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private void mouse() {
		if(Mouse.isButtonDown(0)) {
			int x = Mouse.getX();
			int y = Mouse.getY();
			System.out.println("mouse down at: " + x + ", " + y);
		}
	}
	
	private void keyboard() {
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			System.out.println("SPACE down");
		}
		
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				if(Keyboard.getEventKey() == Keyboard.KEY_A) {
					System.out.println("A");
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_D) {
					System.out.println("D");
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_W) {
					System.out.println("W");
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_S) {
					System.out.println("S");
				}
			}
		}
	}
	
	private void mainLoop() {
		while (!Display.isCloseRequested()) {
			// render OpenGL here
			mouse();
			keyboard();
			Display.update();
		}
		Display.destroy();
	}
}
