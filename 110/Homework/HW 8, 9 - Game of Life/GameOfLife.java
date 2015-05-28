
/**
 * Plays the game of life
 * 
 * @author Nick Alexander 
 * @version 4/19/13
 */

import java.util.*;

public class GameOfLife
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int row = 7;
        int col = 7;
        int[][] board = new int[row][col];
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (board[i][j] == 0)
                {
                    System.out.print("-");
                }
                else if (board[i][j] == 1)
                {
                    System.out.print("x");
                }
            }
            System.out.println();
        }
        boolean done = false;
        do
        {
            System.out.print("1 - Add a being\n" + "2 - Show current board\n" + "3 - Show next generation\n" 
                + "4 - Clear board\n" + "5 - Exit\n"); 
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1)
            {
                System.out.print("Row: ");
                int rowEntry = Integer.parseInt(scanner.nextLine());
                System.out.print("Column: ");
                int colEntry = Integer.parseInt(scanner.nextLine());
                board[rowEntry][colEntry] = 1;
                System.out.println();
            }
            else if (choice == 2)
            {
                for (int i = 0; i < row; i++)
                {
                    for (int j = 0; j < col; j++)
                    {
                        if (board[i][j] == 0)
                        {
                            System.out.print("-");
                        }
                        else if (board[i][j] == 1)
                        {
                            System.out.print("x");
                        }
                    }
                    System.out.println();
                } 
                System.out.println();
            }
            else if (choice == 3)
            {
                for (int i = 1; i < row-1; i++)
                {
                    int c = 0;
                    for (int j = 1; j < col-1; j++)
                    {
                        c = 0;
                        if (board[i][j] == 1)
                        {
                            if (board[i-1][j-1] == 1)
                            {
                                c++;
                            }
                            if (board[i-1][j] == 1)
                            {
                                c++;
                            }
                            if (board[i-1][j+1] == 1)
                            {
                                c++;
                            }
                            if (board[i][j-1] == 1)
                            {
                                c++;
                            }
                            if (board[i][j+1] == 1)
                            {
                                c++;
                            }
                            if (board[i+1][j-1] == 1)
                            {
                                c++;
                            }
                            if (board[i+1][j] == 1)
                            {
                                c++;
                            }
                            if (board[i+1][j+1] == 1)
                            {
                                c++;
                            }
                            if (c < 2)
                            {
                                System.out.println("Lonely");
                                board[i][j] = 0;
                            }
                            if (c > 3)
                            {
                                System.out.println("Overcrowded");
                                board[i][j] = 0;
                            }
                            if (c == 2 || c == 3)
                            {
                                System.out.println("Survives");
                            }
                        }    
                    }    
                }
            }
            else if (choice == 4)
            {
                  for (int i = 0; i < row; i++)
                {
                    for (int j = 0; j < col; j++)
                    {
                       board[i][j] = 0;
                    }
                } 
                System.out.println();
            }
            else if (choice == 5)
            {
                System.out.println("Exit");
                done = true;
            }
        } while ( done != true);
        System.exit(0);
        
    }
}
