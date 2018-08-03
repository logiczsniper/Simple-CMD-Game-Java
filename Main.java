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
        return output;
    }

    public String getPlayerRunOne(int spikePos) {

        prepareSpikes(spikePos);

        String output = String.format(this.baseBoard, " ", " ", "0", " ", "-", "O", "\\", "/", this.spikeZero, ")",
                this.spikeOne, this.spikeTwo, this.spikeThree, this.spikeFour);
        return output;
    }

    public String getPlayerRunTwo(int spikePos) {

        prepareSpikes(spikePos);

        String output = String.format(this.baseBoard, " ", " ", "0", " ", "/", "O", "-", "(", this.spikeZero, "\\",
                this.spikeOne, this.spikeTwo, this.spikeThree, this.spikeFour);
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

class Main {

    public static void runGame() {

        boolean gameEnd = false;
        Object newBoard = new Boards();

        while (gameEnd == false) {

            try {
                Thread.sleep(1500);
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            // TODO: ignoring player jumps and damage, get a loop running using the four following game boards.

            System.out.println(((Boards) newBoard).getPlayerStand(4));
            System.out.println(((Boards) newBoard).getPlayerRunOne(3));
            System.out.println(((Boards) newBoard).getPlayerStand(2));
            System.out.println(((Boards) newBoard).getPlayerRunTwo(1));

            gameEnd = true;
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