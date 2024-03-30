package com.cooksys.ftd.assignments.objects;

import com.cooksys.ftd.assignments.objects.util.MissingImplementationException;

import java.util.Map;

public class SimplifiedRational implements IRational {

        private int numerator;
        private int denominator;

    /**
     * Determines the greatest common denominator for the given values
     *
     * @param a the first value to consider
     * @param b the second value to consider
     * @return the greatest common denominator, or shared factor, of `a` and `b`
     * @throws IllegalArgumentException if a <= 0 or b < 0
     */
    public static int gcd(int a, int b) throws IllegalArgumentException {
        if(a <= 0 || b < 0){
            throw new IllegalArgumentException();
        }
        //It cannot be zero
        int greatestCommonDenominator = 1;

        int dividend = b;
        int divisor = a;
        //Swap value only if a is bigger than b.
        if(a > b){
            dividend = a;
            divisor = b;
        }

        int remainder = dividend % divisor;

        //If given a divisible number than it will not go into the while loop.
        if(remainder == 0){
            greatestCommonDenominator = divisor;
        }

        while(remainder != 0){

            //swap values. New dividend is the previous divisor
            dividend = divisor;
            //swap values. New divisor is the remainder.
            divisor = remainder;
            //raplce remainder with new values
            remainder = dividend % divisor;

            greatestCommonDenominator = divisor;
        }

        return greatestCommonDenominator;
    }

    /**
     * Simplifies the numerator and denominator of a rational value.
     * <p>
     * For example:
     * `simplify(10, 100) = [1, 10]`
     * or:
     * `simplify(0, 10) = [0, 1]`
     *
     * @param numerator   the numerator of the rational value to simplify
     * @param denominator the denominator of the rational value to simplify
     * @return a two element array representation of the simplified numerator and denominator
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public static int[] simplify(int numerator, int denominator) throws IllegalArgumentException {
        if(denominator == 0){
            throw new IllegalArgumentException();
        }
        //Initialize a new array --> [0,0]
        int [] simplifiedNumeratorDenominator = new int[2];

        if(numerator == 0){
            //Output -> [0,1]
            simplifiedNumeratorDenominator[1] = 1;
        }else {
            int divisor = gcd(Math.abs(numerator),Math.abs(denominator));

            simplifiedNumeratorDenominator[0] = numerator / divisor;

            simplifiedNumeratorDenominator[1] = denominator / divisor;
        }

        return simplifiedNumeratorDenominator;
    }

    /**
     * Constructor for rational values of the type:
     * <p>
     * `numerator / denominator`
     * <p>
     * Simplification of numerator/denominator pair should occur in this method.
     * If the numerator is zero, no further simplification can be performed
     *
     * @param numerator   the numerator of the rational value
     * @param denominator the denominator of the rational value
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public SimplifiedRational(int numerator, int denominator) throws IllegalArgumentException {
        if(denominator == 0){
            throw new IllegalArgumentException();
        }

        int [] simplified = simplify(numerator,denominator);

        this.numerator = simplified[0];
        this.denominator = simplified[1];


    }

    /**
     * @return the numerator of this rational number
     */
    @Override
    public int getNumerator() {
        return this.numerator;
    }

    /**
     * @return the denominator of this rational number
     */
    @Override
    public int getDenominator() {
        return this.denominator;
    }

    /**
     * Specializable constructor to take advantage of shared code between Rational and SimplifiedRational
     * <p>
     * Essentially, this method allows us to implement most of IRational methods directly in the interface while
     * preserving the underlying type
     *
     * @param numerator   the numerator of the rational value to construct
     * @param denominator the denominator of the rational value to construct
     * @return the constructed rational value (specifically, a SimplifiedRational value)
     * @throws IllegalArgumentException if the given denominator is 0
     */
    @Override
    public SimplifiedRational construct(int numerator, int denominator) throws IllegalArgumentException {
        if(denominator == 0){
            throw new IllegalArgumentException();
        }
        return new SimplifiedRational(numerator,denominator);
    }

    /**
     * @param obj the object to check this against for equality
     * @return true if the given obj is a rational value and its
     * numerator and denominator are equal to this rational value's numerator and denominator,
     * false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        boolean objEquals = false;
        if(obj instanceof SimplifiedRational) {
            //Technical Debt
            // == or equals operator
            if (((SimplifiedRational) obj).numerator == getNumerator() && ((SimplifiedRational) obj).denominator == getDenominator()) {
                objEquals = true;
            }
        }
        return objEquals;
    }

    /**
     * If this is positive, the string should be of the form `numerator/denominator`
     * <p>
     * If this is negative, the string should be of the form `-numerator/denominator`
     *
     * @return a string representation of this rational value
     */
    @Override
    public String toString() {
        //The same as Rational
        String positiveOrNegativeRational = "";

        if(getNumerator() > 0 && getDenominator() > 0){
            positiveOrNegativeRational = getNumerator() + "/" + getDenominator();
        } else if (getNumerator() < 0 && getDenominator() < 0 ){
            int positiveNumerator = getNumerator() * -1;
            int positiveDenominator = getDenominator() * -1;
            positiveOrNegativeRational = positiveNumerator + "/" + positiveDenominator;
        } else if (getNumerator() < 0 && getDenominator() > 0) {
            positiveOrNegativeRational = getNumerator() + "/" + getDenominator();
        } else if (getNumerator() > 0 && getDenominator() < 0) {
            int negativeNumerator = getNumerator() * -1;
            int positiveDenominator = getDenominator() * -1;
            positiveOrNegativeRational = negativeNumerator + "/" + positiveDenominator;
        }
        return  positiveOrNegativeRational;
    }
}
