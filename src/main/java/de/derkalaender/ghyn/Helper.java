package de.derkalaender.ghyn;

public class Helper {
    public static int createRandomTicks(float secondStart, float secondEnd) {
        return createRandomInt((int) Math.floor(secondStart*10), (int) Math.floor(secondEnd*10));
    }

    public  static int createRandomInt(int start, int end) {
        return (int) Math.floor(Math.random() * (start + end - 1)) + end;
    }

    public static double createChanceInPercent() {
        return Math.random() * 100;
    }
}
