import java.awt.event.InputEvent;
import java.util.Scanner;

public class ClickerBotMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("----- Clicker Bot ------");

		ClickerBotConfig config = new ClickerBotConfig();
		config.getCycles(scanner, args);
		config.getGrowthTime(scanner, args);
		config.getDelayBetweenCycles(args);

		config.calibrateScreen(args);

		config.printConfigCommand();

		ClickerBot clickerBot = new ClickerBot(config);
		System.out.println("Program will start in 5 seconds.");
		sleep(5000);

		for (int i = 0; i < config.cycles; i++) {
			long startTime = System.currentTimeMillis() / 1000;
			System.out.println("Cycle: " + (i+1));

			for (int fieldNumber = 1; fieldNumber <= 22; fieldNumber++) {
				moveAndClick(clickerBot, fieldNumber);
			}

			long cycleTime = (System.currentTimeMillis() / 1000) - startTime;
			System.out.println("Cycle finished ("+cycleTime+"s).");
			if (i < config.cycles - 1 && config.growthTime > cycleTime) {
				int waitingTime = (int) ((config.growthTime - cycleTime));
				System.out.println("Wait " + waitingTime + " for next cycle.");
				sleep((waitingTime + 4) * 1000);
			}
		}

		if (config.delayBetweenCycles > 0) {
			sleep(config.delayBetweenCycles * 1000);
		}
		System.out.println("ClickerBot complete.");
	}

	public static void sleep(int x) {
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static void moveAndClick(ClickerBot clickerBot, int field) {
		int[] location = clickerBot.config.getLocation(field);

		clickerBot.move(location[0], location[1]);
		clickerBot.click(InputEvent.BUTTON1_DOWN_MASK);

		clickerBot.click(InputEvent.BUTTON1_DOWN_MASK);
		double move = (float) 0.2;
		while (move <= 7) {
			clickerBot.move(
					clickerBot.config.getTreasureLocation()[0] + (int)(move * clickerBot.config.getDiffXBetweenFields(1, 2)),
					clickerBot.config.getTreasureLocation()[1]
			);
			clickerBot.click(InputEvent.BUTTON1_DOWN_MASK);
			sleep(20);
			move += 0.9;
		}

		clickerBot.move(
				clickerBot.config.getTreasureCloseButtonLocation()[0],
				clickerBot.config.getTreasureCloseButtonLocation()[1]
		);
		clickerBot.click(InputEvent.BUTTON1_DOWN_MASK);

		clickerBot.move(location[0], location[1]);
		clickerBot.click(InputEvent.BUTTON1_DOWN_MASK);
		sleep(40);
	}
}