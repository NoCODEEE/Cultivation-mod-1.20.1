package net.Byebye007x.firstprotomod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_MOD = "key.category.firstprotomod.mod";

    public static final String KEY_USE_MAGIC = "key.firstprotomod.use_magic";
    public static final String KEY_USE_DASH = "key.firstprotomod.use_dash";

    //Press V
    public static final KeyMapping USE_MAGIC = new KeyMapping(KEY_USE_MAGIC, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY_MOD);
    //Press R
    public static final KeyMapping USE_DASH = new KeyMapping(KEY_USE_DASH, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_MOD);
}
