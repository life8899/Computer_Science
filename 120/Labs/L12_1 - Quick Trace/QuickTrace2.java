public class QuickTrace2
{
    public static void main(String[] args)
    {
        int s = 1;
        int n = 1;
        do
        {
            s = s + n;
            n++;
            System.out.println("s = " + s + " n = " + n);
        } while (s < 3 * n);
    }
}