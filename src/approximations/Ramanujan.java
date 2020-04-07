package approximations;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class generates an approximation of pi using Srinivasa Ramanujan's
 * formula based on modular equations.
 * 
 * @author Kyler McMullin
 *
 */
public class Ramanujan {

	/**
	 * The number of iterations of the series to perform.
	 */
	private int iterations;
	/**
	 * The number of decimal places to round the approximation off to.
	 */
	private int decimalPlaces;

	/**
	 * Instantiates the Ramanujan approximation class.
	 * 
	 * @param iterations    The number of iterations of the series to perform.
	 * @param decimalPlaces The number of decimal places to round the approximation
	 *                      off to.
	 */
	public Ramanujan(int iterations, int decimalPlaces) {
		this.iterations = iterations;
		this.decimalPlaces = decimalPlaces;
	}

	/**
	 * Calculates and returns the approximation of pi, rounded off to the set number
	 * of decimal places.
	 * 
	 * WARNING: This method requires recursion. Computation time is greatly
	 * increased with higher values of iterations. Stack overflows also occur with
	 * high iteration counts.
	 * 
	 * @return piApproximation A BigDecimal approximation of pi
	 */
	public BigDecimal approx() {

		BigDecimal numerator = BigDecimal.valueOf(9801);
		BigDecimal demonCoeff = BigDecimal.valueOf(2)
				.multiply(RootCalculator.root(2, decimalPlaces, RootCalculator.UPPER_BOUND));

		BigDecimal seriesValue = BigDecimal.ZERO;

		for (int i = 0; i < iterations; i++) {
			seriesValue = seriesValue.add((new BigDecimalFactorial().factorial(BigDecimal.valueOf(4 * i))
					.multiply(BigDecimal.valueOf(1103 + (26390 * i))))
							.divide((new BigDecimalFactorial().factorial(BigDecimal.valueOf(i)).pow(4)).multiply(
									BigDecimal.valueOf(396).pow(4 * i)), decimalPlaces, RoundingMode.HALF_UP));
		}

		return numerator.divide((demonCoeff.multiply(seriesValue)), decimalPlaces, RoundingMode.HALF_UP);
	}
}
