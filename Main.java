/*
Logan Czernel
This is a very simple game that involves printing characters that look like characters to simulate very low level
game graphics. The main purpose of the project was to give me a more hands on learning experience as I learn Java.
 */


import java.util.Scanner;


class Boards {
    private String spikeZero = "_";
    private String spikeOne = " ";
    private String spikeTwo = " ";
    private String spikeThree = " ";
    private String spikeFour = " ";
    private String baseBoard = "" +
            "|---------------------------|\n" +
            "|                           |\n" +
            "|                           |\n" +
            "|             %s             |\n" +
            "|            %s%s%s            |\n" +
            "|            %s%s%s            |\n" +
            "|____________%s%s%s____________|\n" +
            "|             %s             |\n" +
            "|             %s             |\n" +
            "|             %s             |\n" +
            "|             %s             |\n" +
            "|---------------------------|\n";

    private void resetSpikes() {
        this.spikeZero = "_";
        this.spikeOne = " ";
        this.spikeTwo = " ";
        this.spikeThree = " ";
        this.spikeFour = " ";
    }

    String getPlayerStand(int spikePos) {

        prepareSpikes(spikePos);

        String output = String.format(this.baseBoard, " ", " ", "0", " ", "/", "O", "\\", "/", this.spikeZero, "\\",
                this.spikeOne, this.spikeTwo, this.spikeThree, this.spikeFour);

        output = addFinalPart(spikePos, output);

        return output;
    }

    String getPlayerRunOne(int spikePos) {

        prepareSpikes(spikePos);

        String output = String.format(this.baseBoard, " ", " ", "0", " ", "-", "O", "\\", "/", this.spikeZero, ")",
                this.spikeOne, this.spikeTwo, this.spikeThree, this.spikeFour);

        output = addFinalPart(spikePos, output);

        return output;
    }

    String getPlayerRunTwo(int spikePos) {

        prepareSpikes(spikePos);

        String output = String.format(this.baseBoard, " ", " ", "0", " ", "/", "O", "-", "(", this.spikeZero, "\\",
                this.spikeOne, this.spikeTwo, this.spikeThree, this.spikeFour);

        output = addFinalPart(spikePos, output);

        return output;
    }

    private String addFinalPart(int spikePos, String output) {
        if (spikePos == 0) {
            output += "\n\nGAME OVER";
        } else {
            output += "Press 'ENTER' to jump!";
        }
        return output;
    }

    public String getPlayerUp(int spikePos) {

        prepareSpikes(spikePos);

        return String.format(this.baseBoard, "0", "\\", "O", "/", "<", " ", ">", "_", this.spikeZero, "_",
                this.spikeOne, this.spikeTwo, this.spikeThree, this.spikeFour);
    }

    private void prepareSpikes(int spikePos) {

        resetSpikes();

        if (spikePos == 0) {
            this.spikeZero = "^";
        } else if (spikePos == 1) {
            this.spikeOne = "^";
        } else if (spikePos == 2) {
            this.spikeTwo = "^";
        } else if (spikePos == 3) {
            this.spikeThree = "^";
        } else if (spikePos == 4) {
            this.spikeFour = "^";
        }
    }
}

public class Main {

    public static class MyThread extends Thread {

        Scanner user_input;

        @Override
        public void run() {
            user_input = new Scanner(System.in);
            String input = this.user_input.nextLine();

            if (input.isEmpty()) {
                System.out.print("-You Leap Gracefully-");
            }
        }
    }

    private static void mySleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private static void runGame() {

        boolean gameEnd = false;
        int spikePos = 4;
        int counter = 0;
        int level = 0;
        double playerMoveTimeFloat = 3000.00;
        Boards newBoard = new Boards();
        String[] mainBoards = {newBoard.getPlayerStand(4), newBoard.getPlayerRunOne(3),
                newBoard.getPlayerStand(2), newBoard.getPlayerRunTwo(1),
                newBoard.getPlayerStand(1)};

        System.out.println("\n\nLEVEL: 0");

        mySleep(1);

        while (!gameEnd) {

            // boolean hasBoardPrinted = false;
            System.out.println("\n\n" + mainBoards[counter]);
            // hasBoardPrinted = true;
            MyThread myThread = new MyThread();

            if (!mainBoards[counter].contains("GAME OVER")) {
                myThread.start();
            }

            int playerMoveTime = (int) playerMoveTimeFloat;
            mySleep(playerMoveTime);
            playerMoveTimeFloat = playerMoveTimeFloat * 0.985;

            //noinspection deprecation
            myThread.stop();

            if (mainBoards[counter].contains("GAME OVER")) {
                gameEnd = true;
                System.out.println("You made it to level " + level + ".");
            }

            if (spikePos > 0) {
                spikePos--;
                counter++;
            } else if (spikePos == 0) {
                spikePos = 4;
                counter = 0;
                level++;
                System.out.println("\n\nLEVEL: " + level);

                mySleep(1);
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("\n\nWelcome to StickWor|d...\n\nPress 'SPACE' followed by 'ENTER' to start!\n\n");
        Scanner user_input = new Scanner(System.in);
        String input = user_input.nextLine();
        String search = " ";

        if (input.contains(search)) {
            runGame();
        }
    }

}