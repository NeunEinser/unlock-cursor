package unlockcursor.mixins;

import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import unlockcursor.UnlockCursorOptionHolder;

@Mixin(GameOptions.class)
public class GameOptionsMixin implements UnlockCursorOptionHolder {
	@Shadow
	public KeyBinding[] keysAll;
	public KeyBinding unlockCursor;

	@Inject(method = "load()V", at = @At("HEAD"))
	public void addUnlockCursorKeybind(CallbackInfo info) {
		unlockCursor = new KeyBinding("Unlock Cursor", InputUtil.Type.MOUSE, 2, "key.categories.misc");
		final KeyBinding[] overwrittenKeysAll = new KeyBinding[keysAll.length + 1];

		System.arraycopy(keysAll, 0, overwrittenKeysAll, 1, keysAll.length);
		overwrittenKeysAll[0] = unlockCursor;

		keysAll = overwrittenKeysAll;
	}

	@Override
	public KeyBinding getUnlockCursorKeybind() {
		return unlockCursor;
	}
}
