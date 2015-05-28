public class SumTest
{
  public static void main(String[] args)
  {
    int n1 = 10, n2 = 1000, n3 = 3000;
    Sum mySum1 = new Sum();
    SumNumbers mySumNumbers1 = new SumNumbers(n1, mySum1);
    Thread myThread1 = new Thread(mySumNumbers1, "Fred");
    Sum mySum2 = new Sum();
    SumNumbers mySumNumbers2 = new SumNumbers(n2, mySum2);
    Thread myThread2 = new Thread(mySumNumbers2, "Mary");
    Sum mySum3 = new Sum();
    SumNumbers mySumNumbers3 = new SumNumbers(n3, mySum3);
    Thread myThread3 = new Thread(mySumNumbers3, "Sandy");
    System.out.println("Starting the threads");
    myThread1.start();
    myThread2.start();
    myThread3.start();
    System.out.println("The threads have started!");
    System.out.println("Waiting on the threads...");
    try
    {
        myThread1.join();
        myThread2.join();
        myThread3.join();
    } catch (InterruptedException ie) {
        System.err.println(ie);
    }
    System.out.println("The sum of 1 to " + n1 + " is " + mySum1.get());
    System.out.println("The sum of 1 to " + n2 + " is " + mySum2.get());
    System.out.println("The sum of 1 to " + n3 + " is " + mySum3.get());
    System.exit(0);
  }
}
