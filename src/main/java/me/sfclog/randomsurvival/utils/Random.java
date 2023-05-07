package me.sfclog.randomsurvival.utils;

public class Random {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
