/**
 * Computes each individual section of the Dot Product
 *
 * @author Kat Richards
 * @version 10/10/2014
 */
public class Worker implements Runnable
{
	private int row;
	private int col;
	private int[][] matrixA;
	private int[][] matrixB;
	private int[][] matrixC;

	public Worker(int row, int col, int[][] matrixA, int[][] matrixB, int[][] matrixC)
	{
		this.row = row;
		this.col = col;
		this.matrixA = matrixA;
		this.matrixB = matrixB;
		this.matrixC = matrixC;
	}

	public void run()
	{
		for (int i = 0; i < matrixA[0].length; i++)
			matrixC[row][col] += matrixA[row][i] * matrixB[i][col];
	}
}