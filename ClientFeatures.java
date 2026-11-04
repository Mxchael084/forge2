package dev.rupture.client;

/** Client-side toggles. These are deliberately limited to local, non-combat QoL features. */
public final class ClientFeatures {
    private ClientFeatures() {}
    public static boolean coordinates = true;
    public static boolean fps = true;
    public static boolean sprintAssist = false;
    public static boolean fullbright = false;
}
