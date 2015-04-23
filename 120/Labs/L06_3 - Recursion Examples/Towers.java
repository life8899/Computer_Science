public class Towers
{

	public static void towersOfHanoi(int n, int source, int destination, int helper)
	{
	  if(n==1)
	  {
		  System.out.println("Move a disk from "+source +" to " +destination);
	  }
	  else
	  {
		  towersOfHanoi(n-1, source, helper, destination);
		  System.out.println("Move a disk from "+source +" to " +destination);
		  towersOfHanoi(n-1, helper, destination, source);
	  }
	}

	public static void main(String[] args)
	{
		towersOfHanoi(4,1,3,2);
	}

}