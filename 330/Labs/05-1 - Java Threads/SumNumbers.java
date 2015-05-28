public class SumNumbers implements Runnable
{
  private int n;
  private Sum mySum;
  public SumNumbers(int n, Sum sumObject)
  {
    this.n = n;
    mySum = sumObject;
  }
  // According to the Java API, what must the name of this method be?
  public void run()
  {
    //Fill in the blank with code that gets the name of the current thread
    System.out.println(Thread.currentThread().getName() + " is computing....");
    int total = 0;
    for (int i = 0; i <= n; i++)
    {
      total += i;
      try
      {
        //Fill in the blank with code that puts the current thread to sleep for one ms
        Thread.currentThread().sleep(1);
      }
      catch (InterruptedException ie)
      {
        System.err.println(ie);
      }
    }
    //Fill in the blank with code that gets the name of the current thread
    System.out.println(Thread.currentThread().getName() + " is done!!");
    mySum.set(total);
  }
}