package approximations;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class generates an approximation of pi using the Madhava-Leibniz
 * infinite series.
 * 
 * @author Kyler McMullin
 *
 */
public class MadhavaLeibniz {
	
	/**
	 * Integer value representation of the number of series iterations
	 */
	private int seriesIterations;
	/**
	 * Integer value representation of the number of decimal places that calculations should be rounded to
	 */
	private int decimalPlaces;
	/**
	 * BigDecimal representation of the value of the square root of 12
	 */
	private BigDecimal root12;

	/**
	 * Instantiates an instance of the Madhava-Leibniz approximation class.
	 * 
	 * @param seriesIterations     The number of iterations of the Madhava-Leibniz
	 *                             series to perform. Higher numbers mean more
	 *                             accuracy at the cost of computation time.
	 * @param decimalPlaces        The number of decimal places to round the
	 *                             approximation to.
	 */
	public MadhavaLeibniz(int seriesIterations, int decimalPlaces) {
		this.seriesIterations = seriesIterations;
		this.decimalPlaces = decimalPlaces;
		root12 = RootCalculator.root(12, decimalPlaces, RootCalculator.UPPER_BOUND);
	}

	/**
	 * Calculates and returns the approximation of pi, rounded off to the set number of decimal places.
	 * 
	 * NOTE: This method can be very time consuming, based on the value of seriesIterations.
	 * 
	 * @return piApproximation A BigDecimal approximation of pi
	 */
	public BigDecimal approx() {

		BigDecimal seriesValue;

		seriesValue = BigDecimal.ONE;

		if (seriesIterations != 0) {
			BigDecimal seriesObj = BigDecimal.ONE;
			for (int i = 1; i < seriesIterations; i++) {
				seriesObj = BigDecimal.valueOf(1).divide(
						(BigDecimal.valueOf(2 * i).add(BigDecimal.valueOf(1))).multiply(BigDecimal.valueOf(3).pow(i)),
						decimalPlaces, RoundingMode.HALF_UP);
				if (i % 2 == 1) {
					seriesValue = seriesValue.subtract(seriesObj);
				} else {
					seriesValue = seriesValue.add(seriesObj);
				}
			}
		}

		return root12.multiply(seriesValue).setScale(decimalPlaces, RoundingMode.HALF_UP);
	}
}
