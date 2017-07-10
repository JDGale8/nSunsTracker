package dallasapps.nsunstracker.util;

/**
 * Created by katy on 7/8/17.
 */


/**
 * calculates weights for each set
 * if kg is true, round to nearest 2.5, if kg is false, we're in lbs and round to nearest 5
 */
public class WeightCalculator {

    private boolean isKg;
    private double roundingFactor;

    public WeightCalculator(boolean isKg) {
        calculateRoundingFactor();
    }

    public void calculateRoundingFactor() {
        if(isKg) {
            this.roundingFactor = 2.5;
        }
        else {
            this.roundingFactor = 5.0;
        }

    }
    public void setIsKg(boolean isKg) {
        this.isKg = isKg;
        calculateRoundingFactor();
    }

    public double calculateWeight(double oneRepMax, double factor) {
        double retval = Math.round(oneRepMax *  0.65 / roundingFactor) * roundingFactor;
        if (retval > 999) {
            return 999;
        }
        else {
            return retval;
        }
    }
}
