package com.virtuwear.rest.utility;

public class CalculateReward {
    public static int calculateReward(int milestone) {
        return switch (milestone) {
            case 3 -> 10;
            case 7 -> 20;
            case 12 -> 25;
            case 20 -> 25;
            case 35 -> 25;
            case 50 -> 25;
            default -> 0;
        };
    }
}
