package gl;

import org.lwjgl.Sys;

public class TimeUtil {
	static long lastFrame;
	
	public static long getTime() {
		return Sys.getTime();
	}
	
	public static int getDelta() {
		long time = getTime();
		int delta = (int)(time - lastFrame);
		lastFrame = time;
		return delta;
	}
}
