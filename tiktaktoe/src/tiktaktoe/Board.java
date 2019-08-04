package tiktaktoe;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final int No_player = 0;
    public static final int Computer_x = 1;
    public static final int User_0 = 2;
    private int[][] board = new int[3][3];
    public Point computerMove;

    public boolean gameover() {
        return playerwon(Computer_x) || playerwon(User_0) || getAvailableCells().isEmpty();

    }
    

    public boolean playerwon(int player) {

        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == player)
                || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == player)) {
            return true;
        }

        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == player)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == player)) {
                return true;
            }
        }
        return false;
    }

    public List<Point> getAvailableCells() {
        List<Point> availableCells = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == No_player) {
                    availableCells.add(new Point(i, j));
                }
            }
        }
        return availableCells;
    }

    public boolean placeMove(Point point, int player) {

        if (board[point.x][point.y] != No_player) {
            return false;
        }

        board[point.x][point.y] = player;
        return true;
    }

    public void displayBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String value = "?";

                if (board[i][j] == Computer_x) {
                    value = "x";
                } else if (board[i][j] == User_0) {
                    value = "0";
                }
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int minimax(int depth, int turn) {
        if (playerwon(Computer_x)) {
            return 1;
        }
        if (playerwon(User_0)) {
            return -1;
        }
        List<Point> availableCells = getAvailableCells();
        if (availableCells.isEmpty()) 
            return 0;
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < availableCells.size(); i++) {
            Point point = availableCells.get(i);

        
            if (turn == Computer_x) { 
                placeMove(point, Computer_x);
                int currentScore = minimax(depth + 1, User_0); 
                max = Math.max(currentScore, max);

                if (depth == 0) 
                    System.out.println("computer score for posiition " + point + " = " + currentScore);
                
 
                if (currentScore >= 0) 
                    if (depth == 0) 
                        computerMove = point;
                    
                
                if (currentScore == 1) {
                    board[point.x][point.y] = No_player;
                    break;
                }
                if (i == availableCells.size() - 1 && max < 0) 
                    if (depth == 0) 
                        computerMove = point;
                    
                

            } else if (turn == User_0) {
                placeMove(point, User_0);
                int currentScore = minimax(depth + 1, Computer_x);
                min = Math.min(currentScore, min);

                if (min == -1) {
                    board[point.x][point.y] = No_player;
                    break;
                }
            }
            board[point.x][point.y] = No_player;
        }
        return turn == Computer_x ? max : min;
    }
            
 }


