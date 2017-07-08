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

    public double calculateBench1(double oneRepMax) {
        return (oneRepMax *  0.65) / roundingFactor * roundingFactor;
    }
    public double calculateBench2(double oneRepMax) {
        return (oneRepMax *  0.75) / roundingFactor * roundingFactor;
    }
    public double calculateBench3(double oneRepMax) {
        return (oneRepMax *  0.85) / roundingFactor * roundingFactor;
    }
    public double calculateBench4(double oneRepMax) {
        return (oneRepMax *  0.85) / roundingFactor * roundingFactor;
    }
    public double calculateBench5(double oneRepMax) {
        return (oneRepMax *  0.85) / roundingFactor * roundingFactor;
    }
    public double calculateBench6(double oneRepMax) {
        return (oneRepMax *  0.8) / roundingFactor * roundingFactor;
    }
    public double calculateBench7(double oneRepMax) {
        return (oneRepMax *  0.75) / roundingFactor * roundingFactor;
    }
    public double calculateBench8(double oneRepMax) {
        return (oneRepMax *  0.7) / roundingFactor * roundingFactor;
    }
    public double calculateBench9(double oneRepMax) {
        return (oneRepMax *  0.65) / roundingFactor * roundingFactor;
    }
    
    public double calculateOhp1(double oneRepMax) {
        return (oneRepMax * 0.5) / roundingFactor * roundingFactor;
    }
    public double calculateOhp2(double oneRepMax) {
        return (oneRepMax * 0.6) / roundingFactor * roundingFactor;
    }
    public double calculateOhp3(double oneRepMax) {
        return (oneRepMax * 0.7) / roundingFactor * roundingFactor;
    }
    public double calculateOhp4(double oneRepMax) {
        return (oneRepMax * 0.7) / roundingFactor * roundingFactor;
    }
    public double calculateOhp5(double oneRepMax) {
        return (oneRepMax * 0.7) / roundingFactor * roundingFactor;
    }
    public double calculateOhp6(double oneRepMax) {
        return (oneRepMax * 0.7) / roundingFactor * roundingFactor;
    }
    public double calculateOhp7(double oneRepMax) {
        return (oneRepMax * 0.7) / roundingFactor * roundingFactor;
    }
    public double calculateOhp8(double oneRepMax) {
        return (oneRepMax * 0.7) / roundingFactor * roundingFactor;
    }

}
