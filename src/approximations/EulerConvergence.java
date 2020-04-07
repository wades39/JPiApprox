package approximations;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class generates an approximation of pi based upon the Euler Convergence
 * Transformation.
 * 
 * @author Kyler McMullin
 *
 */
public class EulerConvergence {

	/**
	 * Integer representation of the number of iterations through the series.
	 */
	private int seriesIterations;
	/**
	 * Integer representation of the number of decimal places to round the
	 * approximation off to.
	 */
	private int decimalPlaces;

	/**
	 * Instantiates the Euler Convergence approximation.
	 * 
	 * @param seriesIterations The number of iterations of the Euler Convergence
	 *                         Transformation series to do.
	 * @param decimalPlaces    The number of decimal places to round the
	 *                         approximation off to.
	 */
	public EulerConvergence(int seriesIterations, int decimalPlaces) {
		this.seriesIterations = seriesIterations;
		this.decimalPlaces = decimalPlaces;
	}

	/**
	 * Returns a BigDecimal representation of the approximation of pi.
	 * 
	 * WARNING: This method approximates pi recursively. Large iteration counts
	 * significantly increase computation time.
	 * 
	 * @return piApproximation A BigDecimal approximation of pi.
	 */
	public BigDecimal approx() {
		return approxRecursion(seriesIterations + 1, 1, 3).multiply(BigDecimal.valueOf(2)).setScale(decimalPlaces,
				RoundingMode.HALF_UP);
	}

	/**
	 * Recursively approximate pi.
	 * 
	 * Internal use only!!!
	 * 
	 * @param iterations Indicates whether or not to continue iterating recursively.
	 * @param num        Integer value of the numerator of the recursive function.
	 * @param denom      Integer value of the denominator of the recursive function.
	 * @return piApproximation A BigInteger approximation of pi.
	 */
	private BigDecimal approxRecursion(int iterations, int num, int denom) {
		// return 0 if iteration count has been met
		if (iterations == 0) {
			return BigDecimal.ZERO;
		}

		return BigDecimal.ONE
				.add(((BigDecimal.valueOf(num).divide(BigDecimal.valueOf(denom), decimalPlaces, RoundingMode.HALF_UP))
						.multiply(approxRecursion(iterations - 1, num + 1, denom + 2))));
	}

}
