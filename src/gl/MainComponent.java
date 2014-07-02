package gl;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class MainComponent {
	MouseUtil mouseUtil;
	KeyboardUtil keyboardUtil;
	TimeUtil timeUtil;
	
	public MainComponent() {
		mouseUtil = new MouseUtil();
		keyboardUtil = new KeyboardUtil();
		timeUtil = new TimeUtil(); 
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
		mouseUtil.printCoords();
	}
	
	private void keyboard() {
		keyboardUtil.printKeys();
	}
	
	private void mainLoop() {
		while (!Display.isCloseRequested()) {
			// render OpenGL here
			mouse();
			keyboard();
			update(timeUtil.getDelta());
			timeUtil.updateFps();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	private void update(int delta) {
		
	}
}
