package com.cooksys.ftd.assignments.objects;

import com.cooksys.ftd.assignments.objects.util.MissingImplementationException;

public class Rational implements IRational {
    private int numerator;
    private int denominator;

    /**
     * Constructor for rational values of the type:
     * <p>
     * `numerator / denominator`
     * <p>
     * No simplification of the numerator/denominator pair should occur in this method.
     *
     * @param numerator   the numerator of the rational value
     * @param denominator the denominator of the rational value
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public Rational(int numerator, int denominator) throws IllegalArgumentException {
        if (denominator == 0) {
            throw new IllegalArgumentException();
        }
        this.numerator = numerator;
        this.denominator = denominator;
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
     * @return the constructed rational value
     * @throws IllegalArgumentException if the given denominator is 0
     */
    @Override
    public Rational construct(int numerator, int denominator) throws IllegalArgumentException {
        if (denominator == 0) {
            throw new IllegalArgumentException();
        }

        return new Rational(numerator, denominator);
    }

    /**
     * @param obj the object to check this against for equality
     * @return true if the given obj is a rational value and its
     * numerator and denominator are equal to this rational value's numerator and denominator,
     * false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        //It gives me a compile error when I only use return statements. Why is that?
        /*
        if(obj instanceof Rational) {
            //Technical Debt
            // == or equals operator
            if (((Rational) obj).numerator == getNumerator() && ((Rational) obj).denominator == getDenominator()) {
                return = true;
            }
        }else{

            return false;
        }
         */

        boolean objEquals = false;
        if (obj instanceof Rational) {
            //Technical Debt
            // == or equals operator
            if (((Rational) obj).numerator == getNumerator() && ((Rational) obj).denominator == getDenominator()) {
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
        //This is the placeholder for the return string.
        String positiveOrNegativeRational = "";

        //Both Position --> Positive Number
        if (getNumerator() > 0 && getDenominator() > 0) {
            positiveOrNegativeRational = getNumerator() + "/" + getDenominator();
            //Both Negative -> Positive Number
        } else if (getNumerator() < 0 && getDenominator() < 0) {
            //Changing the negative numbers into positives
            int positiveNumerator = getNumerator() * -1;
            int positiveDenominator = getDenominator() * -1;
            positiveOrNegativeRational = positiveNumerator + "/" + positiveDenominator;
            //Numerator negative and denominator postive --> Negative Number
        } else if (getNumerator() < 0 && getDenominator() > 0) {
            positiveOrNegativeRational = getNumerator() + "/" + getDenominator();
            //Numerator positive and denominator negative --> Negative Number
        } else if (getNumerator() > 0 && getDenominator() < 0) {
            //Channing to one negative number and one positive number
            int negativeNumerator = getNumerator() * -1;
            int positiveDenominator = getDenominator() * -1;
            positiveOrNegativeRational = negativeNumerator + "/" + positiveDenominator;
        }
        return positiveOrNegativeRational;
    }
}
