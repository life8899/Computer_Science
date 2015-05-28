 


/**
 * Game using random number generation
 * 
 * @author Nick Alexander
 * @version 3/29/13
 */

import java.util.*;

public class GuessTheDwarf
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
    
        System.out.println("Welcome to Guess The Dwarf!");
        System.out.print("1 - Bashful\n" + " 2 - Doc\n" + "3 - Dopey\n" + "4 - Grumpy\n" + "5 - Happy\n" + "6 - Sleepy\n" + "7 - Sneezy\n");
        boolean done = false;
        do
        {
            int dwarf = random.nextInt(8);
            System.out.print("Who am I?");
            int guess = Integer.parseInt(scanner.nextLine());
            if (guess == dwarf)
            {
                System.out.println("Yup! That's me!");
                done = true;
                boolean loopDone = false;
                String yes = "Y";
                String no = "N";
                while (loopDone == false)
                {
                    System.out.println("Would you like to play again? (Y/N)");
                    String answer = scanner.nextLine();
                    if (answer.equals(yes))
                    {
                        done = false;
                        loopDone = true;
                    }
                    else if (answer.equals(no))
                    {
                        System.out.println("Thanks for playing Guess The Dwarf!");
                        done = true;
                        loopDone = true;
                    }
                    else
                    {
                        System.out.println("Invalid");
                        loopDone = false;
                    }
                }
            }    
            else
            {
                System.out.println("Nope, not me!");
                done = true;
                boolean loopDone = false;
                String yes = "Y";
                String no = "N";
                while (loopDone == false)
                {
                    System.out.println("Would you like to play again? (Y/N)");
                    String answer = scanner.nextLine();
                    if (answer.equals(yes))
                    {
                        done = false;
                        loopDone = true;
                    }
                    else if (answer.equals(no))
                    {
                        System.out.println("Thanks for playing Guess the Dwarf!");
                        done = true;
                        loopDone = true;
                    }
                    else
                    {
                        System.out.println("Invalid");
                        loopDone = false;
                    }
                }
            }

        } while (done != true);
    }
}    

