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
		MouseUtil.printCoords();
	}
	
	private void keyboard() {
		KeyboardUtil.printKeys();
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
