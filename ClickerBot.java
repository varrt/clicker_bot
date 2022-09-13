import java.awt.*;

public class ClickerBot {

	private Robot robot;
	// delay between click in ms
	private int delay;
	
	public ClickerBot() {
		try {
			robot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
		}
		delay = 300;
	}
	
	public void click(int button) {
		try {
			robot.mousePress(button);
			robot.delay(250);
			robot.mouseRelease(button);
			robot.delay(delay);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void move(int x, int y) {
		robot.mouseMove(x, y);
	}

	public int getX() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		return (int) b.getX();
	}

	public int getY() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		return (int) b.getY();
	}
}

