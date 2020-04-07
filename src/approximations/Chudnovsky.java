package approximations;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * This class generates an approximation of pi based upon the Chudnovsky
 * formula.
 * 
 * @author Kyler McMullin
 *
 */
public class Chudnovsky {

	/**
	 * Integer representation of the number of iterations through the series.
	 */
	private int seriesIterations;
	/**
	 * Integer representation of the number of decimal places to round the
	 * approximation off to.
	 */
	private int decimalPlaces;

	private static final BigDecimalFactorial fact = new BigDecimalFactorial();

	/**
	 * Instantiates the Chudnovsky Formula approximation.
	 * 
	 * @param seriesIterations The number of iterations of the Chudnovsky Formula
	 *                         series to do.
	 * @param decimalPlaces    The number of decimal places to round the
	 *                         approximation off to.
	 */
	public Chudnovsky(int seriesIterations, int decimalPlaces) {
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
		BigDecimal denomCoeff = BigDecimal.valueOf(12);
		BigDecimal numerator = BigDecimal.valueOf(640320).pow(3).sqrt(MathContext.DECIMAL128).setScale(0, RoundingMode.HALF_UP);
		
		BigDecimal seriesValue = BigDecimal.ZERO;
		
		for (int i = 0; i < seriesIterations; i++) {
			BigDecimal seriesNum = fact.factorial(BigDecimal.valueOf(6 * i)).multiply(BigDecimal.valueOf(13591409 + (545140134 * i)));
			
			BigDecimal seriesDenom = (fact.factorial(BigDecimal.valueOf(3 * i)).multiply(
					(fact.factorial(BigDecimal.valueOf(i)).pow(3)).multiply(
							BigDecimal.valueOf(-640320).pow(3 * i))));
			
			seriesValue = seriesValue.add((seriesNum.divide(seriesDenom, decimalPlaces, RoundingMode.HALF_UP)));
		}
		
		return numerator.divide(denomCoeff.multiply(seriesValue), decimalPlaces, RoundingMode.HALF_UP).setScale(decimalPlaces, RoundingMode.HALF_UP);
	}
}
