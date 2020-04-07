package approximations;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * This class generates an approximation of pi based upon the number of randomly
 * generated points that appear within a quarter-circle.
 * 
 * @author Kyler McMullin
 *
 */
public class CircularArea {

	/**
	 * The number of decimal places to round the approximation to.
	 */
	private int decimalPlaces;
	/**
	 * The number of random points to generate.
	 */
	private int numRandomPoints;
	/**
	 * The number of points that appear within the circle segment.
	 */
	private int sumInsideCirc;

	/**
	 * Instantiates the Circular Area class.
	 * 
	 * @param numRandomPoints The number of random points to generate.
	 * @param decimalPlaces   The number of decimal places to round the
	 *                        approximation off to.
	 */
	public CircularArea(int numRandomPoints, int decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
		this.numRandomPoints = numRandomPoints;
	}

	/**
	 * Returns a BigDecimal representation of the approximation of pi.
	 * 
	 * @return piApproximation A BigInteger approximation of pi
	 */
	public BigDecimal approx() {
		for (int i = 0; i < numRandomPoints; i++) {
			BigDecimal rand = randPointRad();
			if ((BigDecimal.ONE.compareTo(rand) == 0) || (BigDecimal.ONE.compareTo(rand) == 1)) {
				sumInsideCirc++;
			}
		}
		return (BigDecimal.valueOf(sumInsideCirc).divide(BigDecimal.valueOf(numRandomPoints)))
				.multiply(BigDecimal.valueOf(4)).setScale(decimalPlaces, RoundingMode.HALF_UP);
	}

	/**
	 * Return a random BigDecimal value between 0 and 1.
	 * 
	 * INTERNAL USE ONLY!!!
	 * 
	 * @return randomBigDecimal A random BigDecimal between 0 and 1.
	 */
	private BigDecimal random() {
		return BigDecimal.valueOf(Math.random()).setScale(decimalPlaces, RoundingMode.HALF_UP);
	}

	/**
	 * Returns the distance of a random point from the origin.
	 * 
	 * @return distFromOrigin BigDecimal representation of the distance of a random
	 *         point from the origin.
	 */
	private BigDecimal randPointRad() {
		return (random().pow(2).add(random().pow(2))).sqrt(MathContext.DECIMAL128).setScale(decimalPlaces, RoundingMode.HALF_UP);
	}

}
