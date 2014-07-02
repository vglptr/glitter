package gl;

import org.lwjgl.input.Keyboard;

public class KeyboardUtil {
	public void printKeys() {
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
}
