package net.Byebye007x.firstprotomod.util;

public class ToggleHandler {
    private static boolean isCultivating = false;

    public static void changeCultivationState() {
        isCultivating = !isCultivating;
    }

    public static boolean isCultivating() {
        return isCultivating;
    }
}
