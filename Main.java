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

    String getPlayerRunOne() {

        prepareSpikes(3);

        String output = String.format(this.baseBoard, " ", " ", "0", " ", "-", "O", "\\", "/", this.spikeZero, ")",
                this.spikeOne, this.spikeTwo, this.spikeThree, this.spikeFour);

        output = addFinalPart(3, output);

        return output;
    }

    String getPlayerRunTwo() {

        prepareSpikes(1);

        String output = String.format(this.baseBoard, " ", " ", "0", " ", "/", "O", "-", "(", this.spikeZero, "\\",
                this.spikeOne, this.spikeTwo, this.spikeThree, this.spikeFour);

        output = addFinalPart(1, output);

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

    String getPlayerUp(int spikePos) {

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

        Scanner user_input = new Scanner(System.in);
        boolean stopLooping = false;
        boolean canJump = true;
        boolean hasJumped = false;

        @Override
        public void run() {

            while (!stopLooping) {
                System.out.print("");
                if (this.canJump) {

                    String input = this.user_input.nextLine();

                    if (input.isEmpty()) {
                        this.hasJumped = true;
                        System.out.print("-You Will Leap Gracefully-");

                        try {
                            this.suspend();
                        } catch (IllegalMonitorStateException ignored) {
                        }

                    }
                    this.canJump = false;
                    this.hasJumped = false;
                }
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
        boolean jumpingBoard = false;
        int spikePos = 4;
        int counter = 0;
        int level = 0;
        double playerMoveTimeFloat = 3000.00;
        Boards newBoard = new Boards();
        String[] mainBoards = {newBoard.getPlayerStand(4), newBoard.getPlayerRunOne(),
                newBoard.getPlayerStand(2), newBoard.getPlayerRunTwo(),
                newBoard.getPlayerStand(0)};

        System.out.println("\n\nLEVEL: 0");
        MyThread myThread = new MyThread();

        mySleep(1);
        myThread.start();

        while (!gameEnd) {

            if (!jumpingBoard) {
                System.out.println("\n\n" + mainBoards[counter]);

                if (mainBoards[counter].contains("GAME OVER")) {
                    gameEnd = true;
                    myThread.stop();
                    System.out.println("You made it to level " + level + ".");
                }

            } else {
                System.out.println("\n\n" + newBoard.getPlayerUp(spikePos));
            }

            myThread.canJump = true;

            int playerMoveTime = (int) playerMoveTimeFloat;
            mySleep(playerMoveTime);

            jumpingBoard = myThread.hasJumped;

            try {
                myThread.resume();
            } catch (IllegalMonitorStateException ignored) {}

            playerMoveTimeFloat = playerMoveTimeFloat * 0.985;

            if (spikePos > 0) {
                spikePos--;
                counter++;
            } else if (spikePos == 0) {
                spikePos = 4;
                counter = 0;
                level++;

                if (!gameEnd) {
                    System.out.println("\n\nLEVEL: " + level);
                }

                mySleep(1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("\n\nWelcome to StickWor|d...\n\nPress 'SPACE' followed by 'ENTER' to start!\n\n");
        Scanner user_input = new Scanner(System.in);
        String input = user_input.nextLine();

        if (input.contains(" ")) {
            runGame();
        }
    }

}