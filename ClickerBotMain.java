import java.awt.event.InputEvent;
import java.util.Scanner;

public class ClickerBotMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("----- Clicker Bot ------");

		System.out.println("Enter the number of plant cycle:");
		int cycles = scanner.nextInt();

		System.out.println("Enter time (seconds) for growth plants:");
		int delayBetweenCycles = scanner.nextInt();
		System.out.println("");

		ClickerBot clickerBot = new ClickerBot();
		System.out.println("Calibrate your screen.");


		System.out.println("Move your cursor to top left field. (You have 5s)");
		sleep(5000);

		int firstFieldX = clickerBot.getX();
		int firstFieldY = clickerBot.getY();

		System.out.println("First field position: " + firstFieldX + "," + firstFieldY);
		System.out.println("Next you move cursor to next right field. (You have 5s)");
		sleep(5000);

		int secondFieldX = clickerBot.getX();
		int secondFieldY = clickerBot.getY();
		System.out.println("Second field position: " + secondFieldX + "," + secondFieldY);

		System.out.println("Program will start in 5 seconds.");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int diff =  secondFieldX - firstFieldX;

		for (int i = 0; i < cycles; i++) {
			System.out.println("Cycle: " + (i+1));

			moveAndClick(clickerBot, firstFieldX, firstFieldY);
			int row = firstFieldX;
			int col = firstFieldY;

			for (int fieldNumber = 1; fieldNumber < 12; fieldNumber++) {
				if (fieldNumber % 4 == 0) {
					row += diff;
					col = firstFieldY;
				} else {
					col += diff;
				}

				moveAndClick(clickerBot, row, col);
			}

			int firstFieldInAreaX = firstFieldX + (7 * diff);
			int firstFieldInAreaY = firstFieldY + (int)(0.5 * diff);

			moveAndClick(clickerBot, firstFieldInAreaX, firstFieldInAreaY);
			moveAndClick(clickerBot, firstFieldInAreaX, firstFieldInAreaY + (int)(1.5 * diff));
			moveAndClick(clickerBot, firstFieldInAreaX + (int)(0.5 * diff), firstFieldInAreaY + (int)(0.6 * diff));
			moveAndClick(clickerBot, firstFieldInAreaX + (int)(1.3 * diff), firstFieldInAreaY);
			moveAndClick(clickerBot, firstFieldInAreaX + (int)(1.3 * diff), firstFieldInAreaY + (int)(1.5 * diff));

			firstFieldInAreaX = firstFieldX + (-1 * diff);
			firstFieldInAreaY = firstFieldY + (int)(5.5 * diff);

			moveAndClick(clickerBot, firstFieldInAreaX, firstFieldInAreaY);
			moveAndClick(clickerBot, firstFieldInAreaX, firstFieldInAreaY + (int)(1.5 * diff));
			moveAndClick(clickerBot, firstFieldInAreaX + (int)(0.5 * diff), firstFieldInAreaY + (int)(0.6 * diff));
			moveAndClick(clickerBot, firstFieldInAreaX + (int)(1.3 * diff), firstFieldInAreaY);
			moveAndClick(clickerBot, firstFieldInAreaX + (int)(1.3 * diff), firstFieldInAreaY + (int)(1.5 * diff));


			System.out.println("Cycle finished.");
			System.out.println("Waiting: " + delayBetweenCycles + "(s)");
			sleep(delayBetweenCycles * 1000);
		}

		System.out.println("ClickerBot complete");
	}

	public static void sleep(int x) {
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static void moveAndClick(ClickerBot clickerBot, int x, int y) {
		clickerBot.move(x, y);
		clickerBot.click(InputEvent.BUTTON1_DOWN_MASK);
		sleep(10);
		clickerBot.click(InputEvent.BUTTON1_DOWN_MASK);
		sleep(1000);
	}
}