import java.util.Scanner;

/**
 * This is a very simple game that involves printing characters that look like characters to simulate very low level
 * game graphics. The main purpose of the project was to give me a more hands on learning experience as I learn Java.
 *
 * @author Logan Czernel
 * @since 01-08-2018
 */


class Boards {

    /**
     * A class that holds methods to create boards and make them accessible to the main game loop.
     * Also stores the required strings which are the building blocks of each game board.
     */

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

    /**
     * This method builds a game board where the player is in the standing position.
     * @param spikePos the position of the spike (in relation to the player).
     * @return the game board that has been constructed specifically as it is required.
     */
    String getPlayerStand(int spikePos) {

        prepareSpikes(spikePos);

        String output = String.format(this.baseBoard, " ", " ", "0", " ", "/", "O", "\\", "/", this.spikeZero, "\\",
                this.spikeOne, this.spikeTwo, this.spikeThree, this.spikeFour);

        output = addFinalPart(spikePos, output);

        return output;
    }

    /**
     * This method builds a game board where the player is in the first running position.
     * @return the game board that has been constructed specifically as it is required.
     */
    String getPlayerRunOne() {

        prepareSpikes(3);

        String output = String.format(this.baseBoard, " ", " ", "0", " ", "-", "O", "\\", "/", this.spikeZero, ")",
                this.spikeOne, this.spikeTwo, this.spikeThree, this.spikeFour);

        output = addFinalPart(3, output);

        return output;
    }

    /**
     * This method builds a game board where the player is in the second running position.
     * @return the game board that has been constructed specifically as it is required.
     */
    String getPlayerRunTwo() {

        prepareSpikes(1);

        String output = String.format(this.baseBoard, " ", " ", "0", " ", "/", "O", "-", "(", this.spikeZero, "\\",
                this.spikeOne, this.spikeTwo, this.spikeThree, this.spikeFour);

        output = addFinalPart(1, output);

        return output;
    }

    /**
     * This method adds the appropriate tag onto the game board string.
     * @param spikePos the position of the spike (in relation to the player).
     * @param output the game board that was constructed but still needs it's tag
     * @return the game board WITH either the 'GAME OVER' tag or the instructions to jump tag.
     */
    private String addFinalPart(int spikePos, String output) {
        if (spikePos == 0) {
            output += "\n\nGAME OVER";
        } else {
            output += "Press 'ENTER' to jump!";
        }
        return output;
    }

    /**
     * This method builds a game board where the player is in the jumping position.
     * @param spikePos the position of the spike (in relation to the player).
     * @return the game board that has been constructed specifically as it is required.
     */
    String getPlayerUp(int spikePos) {

        prepareSpikes(spikePos);

        return String.format(this.baseBoard, "0", "\\", "O", "/", "<", " ", ">", "_", this.spikeZero, "_",
                this.spikeOne, this.spikeTwo, this.spikeThree, this.spikeFour);
    }

    /**
     * This method first resets the spike positions, and then, based on the parameter given, it sets only one of the
     * spike variables to actually contain the 'spike' character.
     * @param spikePos the position that the spike will be in.
     */
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

    /**
     * A class that holds methods to run the game.
     */

    public static class MyThread extends Thread {

        /**
         * This class accepts the user input into the game. It's biggest task is to control the flow of the game.
         * Some examples of this include only operating at certain times and preventing the user from jumping at other
         * times.
         */

        Scanner user_input = new Scanner(System.in);
        boolean stopLooping = false;
        boolean canJump = true;
        boolean hasJumped = false;

        /**
         * Runs the thread, changing the values of canJump and hasJumped along the way to ensure the flow of the game
         * is correct.
         * @deprecated wait() - I have tried to use this.wait() instead, but then the game fails.
         */
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

    /**
     * I isolated this sleep method so that I could use a sleep(like) function with threads without having to
     * include try + catch blocks.
     * @param sleepTime the amount of time to sleep for
     */
    private static void mySleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * This method holds the main game loop. First, it sets up the basic variables required to keep track of multiple
     * things such as when the game should end, a board counter, a level counter, and the list of basic boards to
     * iterate through. Second, an instance of MyThread is created and started. Third, it enters the game loop which
     * prints out the boards and updates them based on whether or not the user jumped, the time for the user to jump
     * (the time space for the user to jump is decreased gradually making the game harder as you progress), and other
     * variables. Once the loop finishes(player died), it prints out the level you made it to.
     * @deprecated I have tried replacing stop with interrupt, and resume with notify. Either of these changes cause
     * failure.
     */
    private static void runGame() {

        boolean gameEnd = false;
        boolean jumpingBoard = false;
        int spikePos = 4;
        int counter = 0;
        int level = 0;
        double playerMoveTimeFloat = 2450.00;
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
            } catch (IllegalMonitorStateException ignored) {
            }

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

    /**
     * The starting method. Welcomes the user to StickWor|d, and takes user input. If the user presses enter, it runs
     * runGame(), entering the game loop.
     * @param args the required param of every main function.
     * @deprecated runGame uses deprecated methods such as Thread.stop().
     */
    public static void main(String[] args) {
        System.out.println("\n\nWelcome to StickWor|d...\n\nPress 'ENTER' to start!\n\n");
        Scanner user_input = new Scanner(System.in);
        String input = user_input.nextLine();

        if (input.isEmpty()) {
            runGame();
        }
    }

}