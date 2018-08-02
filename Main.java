/*
Logan Czernel
This is a very simple game that involves printing characters that look like characters to simulate very low level
game graphics. The main purpose of the project was to give me a more hands on learning experience as I learn Java.
 */


package SimpleJavaGame;


class Boards {
    String baseBoard = "" +
            "|---------------------------|\n" +
            "|                           |\n" +
            "|                           |\n" +
            "|             %s             |\n" +
            "|            %s%s%s            |\n" +
            "|            %s%s%s            |\n" +
            "|____________%s%s%s____________|\n" +
            "|              %s            |\n" +
            "|              %s            |\n" +
            "|              %s            |\n" +
            "|              %s            |\n" +
            "|---------------------------|\n";

    public String getPlayerStand() {
        String output = String.format(this.baseBoard, " ", " ","0", " ", "/", "O", "\\", "/", "_", "\\",
                " ", " ", " ", " ");
        return output;
    }

    public String getPlayerRunOne() {
        String output = String.format(this.baseBoard, " ", " ","0", " ", "-", "O", "\\", "/", "_", ")",
                " ", " ", " ", " ");
        return output;
    }

    public String getPlayerRunTwo() {
        String output = String.format(this.baseBoard, " ", " ","0", " ", "/", "O", "-", "(", "_", "\\",
                " ", " ", " ", " ");
        return output;
    }
}

class Main {

    public static void main(String[] args) {
        System.out.println("\n\nWelcome to StickWor|d...\n\nPress 'SPACE' to start!\n\n");
        Object newBoard = new Boards();
        System.out.println(((Boards) newBoard).getPlayerStand());
        System.out.println(((Boards) newBoard).getPlayerRunOne());
        System.out.println(((Boards) newBoard).getPlayerStand());
        System.out.println(((Boards) newBoard).getPlayerRunTwo());
        System.out.println(((Boards) newBoard).getPlayerStand());
    }

}