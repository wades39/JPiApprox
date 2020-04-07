package approximations;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class generates an approximation of pi using the Gregory-Leibniz
 * infinite series.
 * 
 * @author Kyler McMullin
 *
 */
public class GregoryLeibniz {
	
	/**
	 * Integer value representation of the number of series iterations
	 */
	private int seriesIterations;
	/**
	 * Integer value representation of the number of decimal places that calculations should be rounded to
	 */
	private int decimalPlaces;

	/**
	 * Instantiates an instance of the Madhava-Leibniz approximation class.
	 * 
	 * @param seriesIterations     The number of iterations of the Madhava-Leibniz
	 *                             series to perform. Higher numbers mean more
	 *                             accuracy at the cost of computation time.
	 * @param decimalPlaces        The number of decimal places to round the
	 *                             approximation to.
	 */
	public GregoryLeibniz(int seriesIterations, int decimalPlaces) {
		this.seriesIterations = seriesIterations;
		this.decimalPlaces = decimalPlaces;
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
						(BigDecimal.valueOf(2 * i).add(BigDecimal.valueOf(1))),
						decimalPlaces, RoundingMode.HALF_UP);
				if (i % 2 == 1) {
					seriesValue = seriesValue.subtract(seriesObj);
				} else {
					seriesValue = seriesValue.add(seriesObj);
				}
			}
		}

		return BigDecimal.valueOf(4).multiply(seriesValue).setScale(decimalPlaces, RoundingMode.HALF_UP);
	}
}
