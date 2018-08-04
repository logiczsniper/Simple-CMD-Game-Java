/*
Logan Czernel
This is a very simple game that involves printing characters that look like characters to simulate very low level
game graphics. The main purpose of the project was to give me a more hands on learning experience as I learn Java.
 */


package SimpleJavaGame;

import java.util.Scanner;


class Boards {
    String spikeZero = "_";
    String spikeOne = " ";
    String spikeTwo = " ";
    String spikeThree = " ";
    String spikeFour = " ";
    String baseBoard = "" +
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

    public void resetSpikes() {
        this.spikeZero = "_";
        this.spikeOne = " ";
        this.spikeTwo = " ";
        this.spikeThree = " ";
        this.spikeFour = " ";
    }

    public String getPlayerStand(int spikePos) {

        prepareSpikes(spikePos);

        String output = String.format(this.baseBoard, " ", " ", "0", " ", "/", "O", "\\", "/", this.spikeZero, "\\",
                this.spikeOne, this.spikeTwo, this.spikeThree, this.spikeFour);

        output = addFinalPart(spikePos, output);

        return output;
    }

    public String getPlayerRunOne(int spikePos) {

        prepareSpikes(spikePos);

        String output = String.format(this.baseBoard, " ", " ", "0", " ", "-", "O", "\\", "/", this.spikeZero, ")",
                this.spikeOne, this.spikeTwo, this.spikeThree, this.spikeFour);

        output = addFinalPart(spikePos, output);

        return output;
    }

    public String getPlayerRunTwo(int spikePos) {

        prepareSpikes(spikePos);

        String output = String.format(this.baseBoard, " ", " ", "0", " ", "/", "O", "-", "(", this.spikeZero, "\\",
                this.spikeOne, this.spikeTwo, this.spikeThree, this.spikeFour);

        output = addFinalPart(spikePos, (String) output);

        return output;
    }

    private String addFinalPart(int spikePos, String output) {
        if (spikePos == 0) {
            output += "\n\nGAME OVER";
        } else {
            output += "Press 'SPACE' to prepare to jump and then 'ENTER' to jump!";
        }
        return output;
    }

    public String getPlayerUp(int spikePos) {

        prepareSpikes(spikePos);

        String output = String.format(this.baseBoard, "0", "\\", "O", "/", "<", " ", ">", "_", this.spikeZero, "_",
                this.spikeOne, this.spikeTwo, this.spikeThree, this.spikeFour);
        return output;
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

        String search = " ";
        Scanner user_input = new Scanner(System.in);

        @Override
        public void run() {
            String input = this.user_input.nextLine();

            if (input.indexOf(this.search) != -1) {
                System.out.println("DONT BREEAK PLEASE");
            }
        }

    }

    public static void runGame() {

        boolean gameEnd = false;
        int spikePos = 4;
        int counter = 0;
        double playerMoveTimeFloat = 3000.00;
        Boards newBoard = new Boards();
        String[] mainBoards = {newBoard.getPlayerStand(4), newBoard.getPlayerRunOne(3),
                newBoard.getPlayerStand(2), newBoard.getPlayerRunTwo(1),
                newBoard.getPlayerStand(0)};

        while (gameEnd == false) {

            boolean hasBoardPrinted = false;
            Thread myThread = new MyThread();

            try {
                if (mainBoards[counter].indexOf("GAME OVER") == -1) {
                    myThread.start();
                }
            } catch (Exception e) {
                ;
            }

            try {
                int playerMoveTime = (int) playerMoveTimeFloat;
                Thread.sleep(playerMoveTime);
                playerMoveTimeFloat = playerMoveTimeFloat * 0.985;
                myThread.stop();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            // TODO: fix threads

            System.out.println("\n\n" + mainBoards[counter]);
            hasBoardPrinted = true;

            if (mainBoards[counter].indexOf("GAME OVER") != -1) {
                gameEnd = true;
            }

            if (spikePos > 0) {
                spikePos--;
                counter++;
            } else if (spikePos == 0) {
                spikePos = 4;
                counter = 0;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("\n\nWelcome to StickWor|d...\n\nPress 'SPACE' followed by 'ENTER' to start!\n\n");
        Scanner user_input = new Scanner(System.in);
        String input = user_input.nextLine();
        String search = " ";

        if (input.indexOf(search) != -1) {
            runGame();
        }
    }

}