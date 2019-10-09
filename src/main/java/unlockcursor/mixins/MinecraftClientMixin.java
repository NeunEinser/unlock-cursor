package unlockcursor.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import unlockcursor.UnlockCursorOptionHolder;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
	@Shadow
	public GameOptions options;
	@Shadow
	public Mouse mouse;

	@Inject(method = "handleInputEvents()V", at = @At("HEAD"))
	public void processUnlockMouseKey(CallbackInfo info) {
		while (((UnlockCursorOptionHolder)options).getUnlockCursorKeybind().wasPressed()) {
			mouse.unlockCursor();
		}
	}
}
