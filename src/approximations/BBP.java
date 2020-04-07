package approximations;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class generates an approximation of pi by utilizing Plouffe's BBP digit
 * extraction algorithm.
 * 
 * @author Kyler McMullin
 *
 */
public class BBP {

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
	 * Instantiates the BBP algorithm approximation.
	 * 
	 * @param seriesIterations The number of iterations of the BBP algorithm series
	 *                         to do.
	 * @param decimalPlaces    The number of decimal places to round the
	 *                         approximation off to.
	 */
	public BBP(int seriesIterations, int decimalPlaces) {
		this.seriesIterations = seriesIterations;
		this.decimalPlaces = decimalPlaces;
	}

	/**
	 * Returns a BigDecimal approximation of pi, rounded off to the specified number
	 * of decimal digits.
	 * 
	 * @return piApproximation A BigDecimal approximation of pi.
	 */
	public BigDecimal approx() {

		BigDecimal seriesValue = BigDecimal.ZERO;

		for (int i = 0; i < seriesIterations; i++) {
			seriesValue = seriesValue
					.add((BigDecimal.ONE.divide(BigDecimal.valueOf(16).pow(i), decimalPlaces, RoundingMode.HALF_UP))
							.multiply((BigDecimal.valueOf(4).divide(BigDecimal.valueOf((8 * i) + 1), decimalPlaces, RoundingMode.HALF_UP)
									.subtract(BigDecimal.valueOf(2).divide(BigDecimal.valueOf((8 * i) + 4),
											decimalPlaces, RoundingMode.HALF_UP))
									.subtract(BigDecimal.valueOf(1).divide(BigDecimal.valueOf((8 * i) + 5),
											decimalPlaces, RoundingMode.HALF_UP))
									.subtract(BigDecimal.valueOf(1).divide(BigDecimal.valueOf((8 * i) + 6),
											decimalPlaces, RoundingMode.HALF_UP)))));

		}

		return seriesValue.setScale(decimalPlaces, RoundingMode.HALF_UP);
	}
}
