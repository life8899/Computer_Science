
/**
 * A rousing game of Rock, Paper, Scissors!
 * 
 * @author Nick Alexander 
 * @version 3/29/13
 */

import java.util.*;

public class RPS
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean done = false;
        String rock = "rock";
        String paper = "paper";
        String scissors = "scissors";
        String computer;
        String answer;
        String yes = "Y";
        String no = "N";
        do
        {
            System.out.println("Hope you're ready to play because you're stuck in a loop!");
            int humScore = 0;
            int cpuScore = 0;
            for (int i=0; i <3; i++)
            {
                boolean choiceDone = false;
                do
                {
                    System.out.print("Rock, Paper, or Scissors?:  ");
                    answer = scanner.nextLine();
                    if (!answer.equalsIgnoreCase(rock) && !answer.equalsIgnoreCase(paper) && !answer.equalsIgnoreCase(scissors))
                    {
                        System.out.println("Invalid Choice!");
                        choiceDone = false;
                    }
                    else
                    {
                        choiceDone = true;
                    }
                } while ( choiceDone != true);
                int comp = random.nextInt(3);
                if (comp == 0)
                {
                    computer = "rock"; 
                }
                else if (comp == 1)
                {
                    computer = "paper";
                }
                else
                {
                    computer = "scissors";
                }
                System.out.println("I chose: " + computer);
                
                // Human Choice = answer; Computer Choice = computer
                if (answer.equalsIgnoreCase(computer))
                {
                    System.out.println("Tie!\n");
                }
                else if (answer.equalsIgnoreCase(rock) && computer.equalsIgnoreCase(paper))
                {
                    System.out.println("Computer wins!\n");
                    cpuScore++;
                }
                else if (answer.equalsIgnoreCase(rock) && computer.equalsIgnoreCase(scissors))
                {
                    System.out.println("Human wins!\n");
                    humScore++;
                }
                else if (answer.equalsIgnoreCase(paper) && computer.equalsIgnoreCase(scissors))
                {
                    System.out.println("Computer wins!\n");
                    cpuScore++;
                }
                else if (answer.equalsIgnoreCase(paper) && computer.equalsIgnoreCase(rock))
                {
                    System.out.println("Human wins!\n");
                    humScore++;
                }
                else if (answer.equalsIgnoreCase(scissors) && computer.equalsIgnoreCase(rock))
                {
                    System.out.println("Computer wins!\n");
                    cpuScore++;
                }
                else if (answer.equalsIgnoreCase(scissors) && computer.equalsIgnoreCase(paper))
                {
                    System.out.println("Human wins!\n");
                    humScore++;
                }
            }
            System.out.println("The score is:");
            System.out.println("Human: " + humScore + " " + "Computer: " + cpuScore);
            if (humScore > cpuScore)
            {
                System.out.println("Human is the Winner!");
            }
            else if (cpuScore > humScore)
            {
                System.out.println("Computer is the Winner!");
            }
            else
            {
                System.out.println("There was a Tie!!!");
            }
            boolean againDone = false;
            do
            {
                System.out.println("Would you like to play again? (Y/N)");
                String again = scanner.nextLine();
                if (again.equalsIgnoreCase(yes))
                {
                    done = false;
                    againDone = true;
                }
                else if (again.equalsIgnoreCase(no))
                {
                    System.out.println("Thanks for playing!");
                    done = true;
                    againDone = true;
                }
                else
                {
                    System.out.println("Invalid!");
                    againDone = false;
                }
            } while (againDone != true);
        } while (done != true);    
    }    
}
