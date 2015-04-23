/**
 * Creates a series of Threads to Compute the Dot Product of Two Matrices
 *
 * @author Nick Alexander
 * @version 10/10/2014
 */
public class DotProduct
{
	private int[][] matrixA;
	private int[][] matrixB;
	private int[][] product;
	private final int ROWS;
	private final int COLS;

	/**
	 * Initializes a DotProduct that Calculates the Dot Product of Two Matrices
	 */
	public DotProduct(int[][] matrixA, int[][] matrixB)
	{
		this.matrixA = matrixA;
		this.matrixB = matrixB;
		this.ROWS = matrixA.length;
		this.COLS = matrixB[0].length;
		this.product = new int[ROWS][COLS];
	}

	/**
	 * Calculates the Dot Product of the Stored Matrices in Multiple Threads
	 * @return Resultant Matrix
	 */
	public int[][] calculate()
	{
		Thread[] workerThreads = new Thread[this.ROWS * this.COLS];
		int counter = 0;
		for (int row = 0; row < this.ROWS; row++)
		{
			for (int col = 0; col < COLS; col++)
			{
				Worker worker = new Worker(row, col, this.matrixA, this.matrixB, this.product);
				workerThreads[counter] = new Thread(worker, "Worker Thread " + String.valueOf(counter));
				worker.run();
				counter++;
			}
		}

		for (Thread workerThread : workerThreads)
		{
			try {
				workerThread.join();
			} catch (InterruptedException interrupt) {
				System.out.println("Interrupted Exception: " + interrupt.getMessage());
			}
		}
		return this.product;
	}

	/** Returns Input Matrix A **/
	public int[][] getMatrixA()
	{
		return this.matrixA;
	}

	/** Returns Input Matrix B **/
	public int[][] getMatrixB()
	{
		return this.matrixB;
	}

	/** Returns Matrices Dot Product **/
	public int[][] getProduct()
	{
		return this.product;
	}
}