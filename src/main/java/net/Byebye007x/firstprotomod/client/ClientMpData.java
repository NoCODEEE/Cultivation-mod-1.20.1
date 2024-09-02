package net.Byebye007x.firstprotomod.client;

public class ClientMpData {
    private static int playerMp;
    private static int playerMaxMp;
    private static int playerMpRegen;

    public static int getPlayerMp() {
        return playerMp;
    }

    public static int getPlayerMaxMp() {
        return playerMaxMp;
    }

    public static int getPlayerMpRegen() {
        return playerMpRegen;
    }

    public static void setPlayerMp(int playerMp, int playerMaxMp, int playerMpRegen) {
        ClientMpData.playerMp = playerMp;
        ClientMpData.playerMaxMp = playerMaxMp;
        ClientMpData.playerMpRegen = playerMpRegen;
    }
}
