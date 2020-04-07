package approximations;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * This class generates an approximation of pi using Viete's formula.
 * 
 * @author Kyler McMullin
 *
 */
public class VieteFormula {

	/**
	 * Integer representation of the number of iterations through the series
	 */
	private int seriesIterations;
	/**
	 * Integer representation of the number of decimal places that the approximation
	 * will be rounded to.
	 */
	private int decimalPlaces;
	/**
	 * Iterative integer
	 */
	private int i;

	/**
	 * Instantiates the Viete Formula approximation.
	 * 
	 * @param seriesIterations The number of iterations that the series will be
	 *                         carried through.
	 * @param decimalPlaces    The number of decimal places that the approximation
	 *                         will be rounded to.
	 */
	public VieteFormula(int seriesIterations, int decimalPlaces) {
		this.seriesIterations = seriesIterations;
		this.decimalPlaces = decimalPlaces;
	}

	/**
	 * Returns a BigDecimal representation of the approximation of pi.
	 * 
	 * @return piApproximation A BigInteger approximation of pi
	 */
	public BigDecimal approx() {
		// Numerator = 2^(iterations + 1)
		BigDecimal numerator = BigDecimal.valueOf(2).pow(seriesIterations + 1);

		BigDecimal denominator = BigDecimal.ONE;

		for (i = 1; i <= seriesIterations; i++) {
			denominator = denominator.multiply(approxRecursive(1, i));
		}

		return numerator.divide(denominator, decimalPlaces, RoundingMode.HALF_UP);
	}

	/**
	 * Recursively calculates the denominator for the series approximation.
	 * 
	 * INTERNAL USE ONLY!!!
	 * 
	 * @param itNum      The number of iterations that the function has already
	 *                   completed.
	 * @param iterations The number of times to iterate through calculating the
	 *                   recursive root denominator.
	 * @return denomApproximation A BigDecimal approximation of the denominator of
	 *         the recursive function.
	 */
	private BigDecimal approxRecursive(int itNum, int iterations) {
		// If the number of already completed iterations matches the required number of
		// iterations, return sqrt(2).
		if (itNum == iterations) {
			return BigDecimal.valueOf(2).sqrt(MathContext.DECIMAL128);
		}

		// Otherwise, recursively calculate the root sqrt(2 + sqrt(2 + sqrt(2...
		return BigDecimal.valueOf(2).add(approxRecursive((itNum + 1), iterations)).sqrt(MathContext.DECIMAL128);
	}
}
