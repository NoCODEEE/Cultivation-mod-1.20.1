package net.Byebye007x.firstprotomod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_MOD = "key.category.firstprotomod.mod";

    public static final String KEY_USE_MAGIC = "key.firstprotomod.use_magic";
    public static final String KEY_USE_DASH = "key.firstprotomod.use_dash";
    public static final String KEY_USE_CLOUD_STEP = "key.firstprotomod.use_cloud_step";
    public static final String KEY_CULTIVATE = "key.firstprotomod.cultivate";

    //Press V
    public static final KeyMapping USE_MAGIC = new KeyMapping(KEY_USE_MAGIC, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY_MOD);
    //Press R
    public static final KeyMapping USE_DASH = new KeyMapping(KEY_USE_DASH, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_MOD);
    //Press space
    public static final KeyMapping USE_CLOUD_STEP = new KeyMapping(KEY_USE_CLOUD_STEP, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_SPACE, KEY_CATEGORY_MOD);
    //Press Y
    public static final KeyMapping CULTIVATING = new KeyMapping(KEY_CULTIVATE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Y, KEY_CATEGORY_MOD);

}
