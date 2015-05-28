public class QuickTrace1
{
    public static void main(String[] args)
    {
        int s = 1;
        int n = 1;
        while (s < 5)
        {
            s = s + n;
            n++;
            System.out.println("s = " + s + " n = " + n);
        }
    }
}
