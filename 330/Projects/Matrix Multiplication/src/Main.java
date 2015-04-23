/**
 * Creates 2 Matrices and Calculates the Dot Product
 *
 * @author Nick Alexander
 * @version 10/11/2014
 */
public class Main
{
	public static void main(String[] args)
	{
		int[][] matrixA = {
				{3, 4, 2}
		};

		int[][] matrixB = {
				{13, 9, 7, 15},
				{8, 7, 4, 6},
				{6, 4, 0, 3}
		};

		DotProduct dotProduct = new DotProduct(matrixA, matrixB);
		int[][] product = dotProduct.calculate();

		System.out.println(matrixToString(dotProduct.getMatrixA()));
		System.out.println(matrixToString(dotProduct.getMatrixB()));
		System.out.println(matrixToString(product));
	}

	private static String matrixToString(int[][] matrix)
	{
		String matrixString = "";
		for (int row = 0; row < matrix.length; row++)
		{
			for (int col = 0; col < matrix[0].length; col++)
				matrixString += String.valueOf(matrix[row][col]) + ((col != matrix[0].length - 1) ? " | " : "\n");
		}
		return matrixString;
	}
}