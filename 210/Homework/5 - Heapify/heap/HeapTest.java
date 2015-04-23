package heap;

import java.util.Scanner;

/**
 * Driver for Heap Classes
 * 
 * @author Nick
 * @version 4/11/14
 */
public class HeapTest
{
	/**
	 * Creates a Heap with User Priority and Tests Heap Methods
	 * 
	 * @param args Runtime Arguments
	 */
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean done = false;
		Heap<String> heap = null;
		
		do
		{
			System.out.print("Max Heap or Min Heap? (Enter max or min) ");
			String input = scanner.nextLine();
			done = true;
			if (input.equalsIgnoreCase("max"))
				heap = new MaxHeap<String>();
			else if (input.equalsIgnoreCase("min"))
				heap = new MinHeap<String>();
			else 
				done = false;
		} while (!done);
		
		System.out.println();
		
		done = false;
		do
		{
			try
			{
				System.out.println("1) Enqueue a Person\n" + "2) Dequeue Person\n" + "3) Print Heap\n" + "4) Quit");
				int input = Integer.parseInt(scanner.nextLine());
				System.out.println();
				switch (input)
				{
				case 1:
					System.out.print("Name to Enqueue: ");
					String eq = scanner.nextLine();
					heap.enqueue(eq);
					break;
				case 2:
					String dq = heap.dequeue();
					if (dq != null)
						System.out.println("Removed " + dq);
					else
						System.out.println("Empty Heap");
					break;
				case 3:
					heap.printAll();
					break;
				case 4:
					done = true;
					break;
				default: 	
					System.out.println("Invalid Input");
				}
			} catch (NumberFormatException e)
			{
				System.out.println("Invalid Input");
			}
			System.out.println();
		} while (!done);
	}
}
