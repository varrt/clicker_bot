import java.awt.*;

public class ClickerBot {
	private Robot robot;

	public ClickerBotConfig config;
	
	public ClickerBot(ClickerBotConfig config) {
		this.config = config;
		try {
			robot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void click(int button) {
		try {
			robot.mousePress(button);
			robot.delay(100);
			robot.mouseRelease(button);
			robot.delay(100);
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

