package approximations;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class allows the user to calculate the square root of a given double.
 * 
 * @author Kyler McMullin
 *
 */
public class RootCalculator {

	/**
	 * 
	 */
	public static final int UPPER_BOUND = 1;
	public static final int LOWER_BOUND = 0;

	/**
	 * Calculates the square root of the given radicand, to the provided decimal
	 * place. The result is returned in BigDecimal format.
	 * 
	 * @param radicand      The double value whose root is to be calculated.
	 * @param decimalPlaces The number of decimal places to calculate the root to.
	 * @param boundType     String representation of which bound to return. "Upper"
	 *                      will return the upper bound, "lower" will return the
	 *                      lower bound.
	 * @return root The square root of the provided radicand, calculated to the
	 *         specified decimal place count.
	 */
	public static BigDecimal root(double radicand, int decimalPlaces, int boundType) {

		// If invalid bound provided, throw new IllegalArgumentException
		if (!(boundType == LOWER_BOUND || boundType == UPPER_BOUND)) {
			throw new IllegalArgumentException();
		}

		BigDecimal rootUpper = BigDecimal.ONE, rootLower = BigDecimal.ONE;
		BigDecimal bdRadicand = BigDecimal.valueOf(radicand);

		// Find the nearest fractional value that is lower than the square root of the
		// radicand.
		for (int i = 1; BigDecimal.valueOf(i).compareTo(bdRadicand) == -1; i++) {
			rootUpper = bdRadicand.divide(BigDecimal.valueOf(i), RoundingMode.FLOOR);
			if ((rootUpper.multiply(rootUpper).compareTo(bdRadicand) == -1)) {
				rootUpper = bdRadicand.divide(BigDecimal.valueOf((i - 1)), RoundingMode.HALF_UP);
				rootLower = bdRadicand.divide(BigDecimal.valueOf((i)), RoundingMode.HALF_UP);
				break;
			}
		}

		// Approximate the root through decimal addition
		BigDecimal delta = BigDecimal.ONE;
		// Add 1E(-i) to the lower root bound until it either equal to or greater than
		// the actual root, apply the value to the upper root bound, then subtract
		// 1E(-i) from the lower root bound.
		for (int i = 0; i < decimalPlaces; i++) {
			delta = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(10).pow(i));
			while ((rootLower.multiply(rootLower)).compareTo(bdRadicand) == -1) {
				rootLower = rootLower.add(delta);
			}
			rootUpper = rootLower;
			rootLower = rootLower.subtract(delta);
		}

		// Return the appropriate bound.
		if (boundType == UPPER_BOUND) {
			return rootUpper;
		}
		return rootLower;
	}

	/**
	 * Calculates the square root of the given radicand, to the provided decimal
	 * place. The result is returned in BigDecimal format.
	 * 
	 * @param radicand      The BigDecimal value whose root is to be calculated.
	 * @param decimalPlaces The number of decimal places to calculate the root to.
	 * @param boundType     String representation of which bound to return. "Upper"
	 *                      will return the upper bound, "lower" will return the
	 *                      lower bound.
	 * @return root The square root of the provided radicand, calculated to the
	 *         specified decimal place count.
	 */
	public static BigDecimal root(BigDecimal radicand, int decimalPlaces, int boundType) {

		// If invalid bound provided, throw new IllegalArgumentException
		if (!(boundType == LOWER_BOUND || boundType == UPPER_BOUND)) {
			throw new IllegalArgumentException();
		}

		BigDecimal rootUpper = BigDecimal.ONE, rootLower = BigDecimal.ONE;

		// Find the nearest fractional value that is lower than the square root of the
		// radicand.
		for (int i = 1; BigDecimal.valueOf(i).compareTo(radicand) == -1; i++) {
			rootUpper = radicand.divide(BigDecimal.valueOf(i), RoundingMode.FLOOR);
			if ((rootUpper.multiply(rootUpper).compareTo(radicand) == -1)) {
				rootUpper = radicand.divide(BigDecimal.valueOf((i - 1)), RoundingMode.HALF_UP);
				rootLower = radicand.divide(BigDecimal.valueOf((i)), RoundingMode.HALF_UP);
				break;
			}
		}

		// Approximate the root through decimal addition
		BigDecimal delta = BigDecimal.ONE;
		// Add 1E(-i) to the lower root bound until it either equal to or greater than
		// the actual root, apply the value to the upper root bound, then subtract
		// 1E(-i) from the lower root bound.
		for (int i = 0; i < decimalPlaces; i++) {
			delta = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(10).pow(i));
			while ((rootLower.multiply(rootLower)).compareTo(radicand) == -1) {
				rootLower = rootLower.add(delta);
			}
			rootUpper = rootLower;
			rootLower = rootLower.subtract(delta);
		}

		// Return the appropriate bound.
		if (boundType == UPPER_BOUND) {
			return rootUpper;
		}
		return rootLower;
	}

}
