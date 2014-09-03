package gl;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class MainComponent {
	private static MainComponent instance;
	private ArrayList<Updatable> updatables;
	private MouseUtil mouseUtil;
	private KeyboardUtil keyboardUtil;
	private TimeUtil timeUtil;
	private Triangle triangle;
	private Square square;

	public MainComponent() {
		instance = this;
		updatables = new ArrayList<>();
		mouseUtil = new MouseUtil();
		keyboardUtil = new KeyboardUtil();
		timeUtil = new TimeUtil();
		initDisplay();
		initUpdatables();
		mainLoop();
	}

	private void initDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setInitialBackground(0.5f, 0.5f, 1f);
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	private void initUpdatables() {
		triangle = new Triangle();
		square = new Square();
		updatables.add(square);
	}

	private void mouse() {
		mouseUtil.printCoords();
	}

	private void keyboard() {
		keyboardUtil.printKeys();
	}

	private void mainLoop() {
		while (!Display.isCloseRequested()) {
			keyboard();
			update(timeUtil.getDelta());
			timeUtil.updateFps();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}

	private void update(int delta) {
		for (Updatable u : updatables) {
			u.update(delta);
		}
	}

	public static void end() {
		instance.dispose();
		instance = null;
		Display.destroy();
		System.exit(0);
	}

	public void dispose() {

	}
}
