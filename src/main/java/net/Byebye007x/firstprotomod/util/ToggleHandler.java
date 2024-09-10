package net.Byebye007x.firstprotomod.util;

public class ToggleHandler {
    private static boolean isCultivating = false;
    private static boolean isAlt = false;

    public static void changeCultivationState() {
        isCultivating = !isCultivating;
    }

    public static boolean isCultivating() {
        return isCultivating;
    }

    public static void changeAltState() {
        isAlt = !isAlt;
    }

    public static boolean isAlt() {
        return isAlt;
    }

}
