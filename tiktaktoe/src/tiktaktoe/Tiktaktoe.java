package tiktaktoe;

import java.awt.Point;
import java.util.Random;
import java.util.Scanner;

public class Tiktaktoe {

    public static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Board b = new Board();
        Scanner scanner = new Scanner(System.in);
        b.displayBoard();
        System.out.println("select turn:\n 1.Computer(x)\n 2.user(0): ");
        int choice = scanner.nextInt();
        if (choice == Board.Computer_x) {
            Point p = new Point(RANDOM.nextInt(3), RANDOM.nextInt(3));

            b.placeMove(p, Board.Computer_x);
            b.displayBoard();
        }
        while (!b.gameover()) {
            boolean moveOK = true;
            do {
                if (!moveOK) {
                    System.out.println("cell already filled");
                }
                System.out.println("your move: ");
                Point userMove = new Point(scanner.nextInt(), scanner.nextInt());
                moveOK = b.placeMove(userMove, Board.User_0);

            } while (!moveOK);

            b.displayBoard();
            if (b.gameover()) {
                break;
            }
            b.minimax(0, Board.Computer_x);

            System.out.println("computer choose position: " + b.computerMove);
            b.placeMove(b.computerMove, Board.Computer_x);
            b.displayBoard();
        }

        if (b.playerwon(Board.Computer_x)) {
            System.out.println("you lost !");
        } else if (b.playerwon(Board.User_0)) {
            System.out.println(" yeeeee you win !");
        } else {
            System.out.println(" drawwww !");
        }

    }

}
