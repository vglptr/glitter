package gl;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class MainComponent {
	private static MainComponent instance;
	MouseUtil mouseUtil;
	KeyboardUtil keyboardUtil;
	TimeUtil timeUtil;
	Triangle triangle;
	//kitalálni a patternt, hogy a controllereket hogyan illesztem ide be
	
	public MainComponent() {
		instance = this;
		mouseUtil = new MouseUtil();
		keyboardUtil = new KeyboardUtil();
		timeUtil = new TimeUtil(); 
		initDisplay();
		triangle = new Triangle();
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
			Display.sync(120);
		}
		Display.destroy();
	}
	
	private void update(int delta) {
		triangle.update(delta);
		
	}
	
	public static void end()
    {
        instance.dispose();
        instance = null;
        Display.destroy();
        System.exit(0);
    }
	
	public void dispose() {
		
	}
}
