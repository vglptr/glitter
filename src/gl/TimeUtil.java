package gl;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

public class TimeUtil {
	private long lastFrame;
	private long lastFps;
	private int fps;

	public TimeUtil() {
		lastFps = getTime();
	}

	public static long getTime() {
		return Sys.getTime();
	}

	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	public void updateFps() {
		if (getTime() - lastFps > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFps += 1000;
		}
		fps++;
	}
}
