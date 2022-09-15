import javax.sound.sampled.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ClickerBotConfig {

    public int cycles;
    public int delayBetweenCycles;
    public int recalibrate = 0;

    public String[] locationsX;
    public String[] locationsY;

    public void getCycles(Scanner scanner, String[] args) {
        int seeds;
        try {
            seeds = Integer.parseInt(args[2]);
            System.out.println("Seeds: " + seeds);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Enter have many seeds you have:");
            seeds = scanner.nextInt();
        }
        cycles = seeds / 22;
    }

    public void getDelayBetweenCycles(Scanner scanner, String[] args) {
        delayBetweenCycles = 0;
        try {
            delayBetweenCycles = Integer.parseInt(args[1]);
            System.out.println("Plant time (seconds): " + delayBetweenCycles);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Enter the growth time:");
            delayBetweenCycles = scanner.nextInt();
        }
    }

    public void calibrateScreen(String[] args) {
        try {
            recalibrate = Integer.parseInt(args[0]);
            if (recalibrate > 0) {
                Files.delete(Path.of("config.cnf"));
            }
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException ignored) {

        }

        File config = new File("config.cnf");
        try {
            if (config.createNewFile()) {
                ClickerBot clickerBot = new ClickerBot(this);
                System.out.println("Calibrate your screen. You must do it only once. After you'll hear a sound you can move to next area. (Start in 5s)");
                sleep(5000);

                int[] x = new int[14];
                int[] y = new int[14];
                for (int i = 0;i < 12; i++) {
                    System.out.println("Move your cursor to middle of field " + (i+1) + ". (You have 2s)");
                    sleep(2000);
                    x[i] = clickerBot.getX();
                    y[i] = clickerBot.getY();
                    System.out.println("Location: " + x[i] + ", " + y[i]);
                    soundBlip();
                }

                System.out.println("Move your cursor to left middle part of treasure modal. (You have 2s)");
                sleep(2000);
                x[12] = clickerBot.getX();
                y[12] = clickerBot.getY();
                System.out.println("Location: " + x[12] + ", " + y[12]);
                soundBlip();

                System.out.println("Now click your treasure and move cursor to close button. (You have 3s)");
                sleep(3000);
                x[13] = clickerBot.getX();
                y[13] = clickerBot.getY();
                System.out.println("Location: " + x[13] + ", " + y[13]);
                soundBlip();


                BufferedWriter bufferedWriter;
                bufferedWriter = new BufferedWriter(new FileWriter(config.getName()));
                bufferedWriter.write(implode(",", x));
                bufferedWriter.newLine();
                bufferedWriter.write(implode(",", y));
                bufferedWriter.close();
            }

            Scanner scanner = new Scanner(config);
            String firstLine = scanner.nextLine();
            locationsX = firstLine.split(",");
            String secondLine  = scanner.nextLine();
            locationsY = secondLine.split(",");
            scanner.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int[] getTreasureLocation() {
        int[] location = new int[2];
        location[0] = Integer.parseInt(locationsX[12]);
        location[1] = Integer.parseInt(locationsY[12]);
        return location;
    }
    public int[] getTreasureCloseButtonLocation() {
        int[] location = new int[2];
        location[0] = Integer.parseInt(locationsX[13]);
        location[1] = Integer.parseInt(locationsY[13]);
        return location;
    }

    public int getDiffXBetweenFields(int field1, int field2) {
        int[] field1Location = getLocation(field1);
        int[] field2Location = getLocation(field2);

        return field2Location[0] - field1Location[0];
    }

    public int[] getLocation(int field) {
        int[] location = new int[2];
        switch (field) {
            case 1 -> {
                location[0] = Integer.parseInt(locationsX[0]);
                location[1] = Integer.parseInt(locationsY[0]);
            }
            case 2 -> {
                location[0] = Integer.parseInt(locationsX[4]);
                location[1] = Integer.parseInt(locationsY[0]);
            }
            case 3 -> {
                location[0] = Integer.parseInt(locationsX[5]);
                location[1] = Integer.parseInt(locationsY[0]);
            }
            case 4 -> {
                location[0] = Integer.parseInt(locationsX[0]);
                location[1] = Integer.parseInt(locationsY[1]);
            }
            case 5 -> {
                location[0] = Integer.parseInt(locationsX[4]);
                location[1] = Integer.parseInt(locationsY[1]);
            }
            case 6 -> {
                location[0] = Integer.parseInt(locationsX[5]);
                location[1] = Integer.parseInt(locationsY[1]);
            }
            case 7 -> {
                location[0] = Integer.parseInt(locationsX[0]);
                location[1] = Integer.parseInt(locationsY[2]);
            }
            case 8 -> {
                location[0] = Integer.parseInt(locationsX[4]);
                location[1] = Integer.parseInt(locationsY[2]);
            }
            case 9 -> {
                location[0] = Integer.parseInt(locationsX[5]);
                location[1] = Integer.parseInt(locationsY[2]);
            }
            case 10 -> {
                location[0] = Integer.parseInt(locationsX[0]);
                location[1] = Integer.parseInt(locationsY[3]);
            }
            case 11 -> {
                location[0] = Integer.parseInt(locationsX[4]);
                location[1] = Integer.parseInt(locationsY[3]);
            }
            case 12 -> {
                location[0] = Integer.parseInt(locationsX[5]);
                location[1] = Integer.parseInt(locationsY[3]);
            }
            case 13 -> {
                location[0] = Integer.parseInt(locationsX[6]);
                location[1] = Integer.parseInt(locationsY[6]);
            }
            case 14 -> {
                location[0] = Integer.parseInt(locationsX[8]);
                location[1] = Integer.parseInt(locationsY[6]);
            }
            case 15 -> {
                location[0] = Integer.parseInt(locationsX[7]);
                location[1] = Integer.parseInt(locationsY[7]);
            }
            case 16 -> {
                location[0] = Integer.parseInt(locationsX[6]);
                location[1] = Integer.parseInt(locationsY[8]);
            }
            case 17 -> {
                location[0] = Integer.parseInt(locationsX[8]);
                location[1] = Integer.parseInt(locationsY[8]);
            }
            case 18 -> {
                location[0] = Integer.parseInt(locationsX[9]);
                location[1] = Integer.parseInt(locationsY[9]);
            }
            case 19 -> {
                location[0] = Integer.parseInt(locationsX[11]);
                location[1] = Integer.parseInt(locationsY[9]);
            }
            case 20 -> {
                location[0] = Integer.parseInt(locationsX[10]);
                location[1] = Integer.parseInt(locationsY[10]);
            }
            case 21 -> {
                location[0] = Integer.parseInt(locationsX[9]);
                location[1] = Integer.parseInt(locationsY[11]);
            }
            case 22 -> {
                location[0] = Integer.parseInt(locationsX[11]);
                location[1] = Integer.parseInt(locationsY[11]);
            }
            default -> throw new ArrayIndexOutOfBoundsException();
        }
        return location;
    }

    public static void sleep(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printConfigCommand() {
        System.out.println("Next time you can run application with specific arguments:");
        System.out.println("java ClickerBotMain " +
                recalibrate + " " +
                delayBetweenCycles + " " +
                cycles
        );
    }

    public static String implode(String glue, int[] locations)
    {
        StringBuilder ret = new StringBuilder();
        for(int i=0;i<locations.length;i++)
        {
            ret.append((i == locations.length - 1) ? locations[i] : locations[i] + glue);
        }
        return ret.toString();
    }

    public void soundBlip() {
        try {
            URL url = this.getClass().getClassLoader().getResource("sound.wav");
            AudioInputStream audioIn;
            if (url != null) {
                audioIn = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}

