import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;

/**
 * Runs Unit Tests with 4 Sets of Matrices
 *
 * @author Nick Alexander
 * @version 10/11/2014
 */
@RunWith(Parameterized.class)
public class DotProductTest
{
	private DotProduct dotProduct;
	private int[][] expected;

	/**
	 * Creates a DotProductTest with Parameters from Parameterized parameters()
	 * @param dotProduct DotProduct Object for Testing
	 * @param expected Excepted Matrix Product
	 */
	public DotProductTest(DotProduct dotProduct, int[][] expected)
	{
		this.dotProduct = dotProduct;
		this.expected = expected;
	}

	/**
	 * Tests DotProduct.calculate()
	 */
	@Test
	public void calculateTest()
	{
		assertArrayEquals(this.expected, this.dotProduct.calculate());
	}

	/**
	 * Supplies Sets of Parameters for Testing DotProduct calculate()
	 * @return Collection wherein each Object[] contains a set of Parameters for Testing
	 * (e.g. DotProduct object and an expected matrix result)
	 */
	@Parameterized.Parameters
	public static Collection<Object[]> parameters()
	{
		// Test 1
		int[][] a = {
				{1, 2, 3},
				{4, 5, 6}
		};
		int[][] b = {
				{7, 8},
				{9, 10},
				{11, 12}
		};
		int[][] expected1 = {
				{58, 64},
				{139, 154}
		};
		DotProduct dotProduct1 = new DotProduct(a, b);

		// Test 2
		a = new int[][] {
				{3, 4, 2}
		};

		b = new int[][] {
				{13, 9, 7, 15},
				{8, 7, 4, 6},
				{6, 4, 0, 3}
		};

		int[][] expected2 = {
				{83, 63, 37, 75}
		};
		DotProduct dotProduct2 = new DotProduct(a, b);

		// Test 3
		a= new int[][] {
				{1, 2},
				{3, 4}
		};

		b = new int[][] {
				{2, 0},
				{1, 2}
		};

		int[][] expected3 = {
				{4, 4},
				{10, 8}
		};
		DotProduct dotProduct3 = new DotProduct(a, b);

		//Test 4
		a= new int[][] {
				{2, 0},
				{1, 2}
		};

		b = new int[][] {
				{1, 2},
				{3, 4}
		};

		int[][] expected4 = {
				{2, 4},
				{7, 10}
		};
		DotProduct dotProduct4 = new DotProduct(a, b);

		return Arrays.asList(new Object[][]{
				{dotProduct1, expected1},
				{dotProduct2, expected2},
				{dotProduct3, expected3},
				{dotProduct4, expected4},
		});
	}
}