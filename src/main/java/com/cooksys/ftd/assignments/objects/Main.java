package com.cooksys.ftd.assignments.objects;

public class Main {
    public static void main(String[] args) {
        SimplifiedRational simplify = new SimplifiedRational(5, 10);

        System.out.println(simplify.getNumerator()); //Output - >  1
        System.out.println(simplify.getDenominator());//Output - > 2
    }
}
