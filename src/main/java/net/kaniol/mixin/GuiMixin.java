package net.kaniol.mixin;

import com.mojang.blaze3d.vertex.PoseStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.Gui;

import net.kaniol.Gathering;

@Mixin(Gui.class)
public class GuiMixin {
    
    @Inject(at = @At("HEAD"), method = "render")
    public void render(PoseStack poseStack, float f, CallbackInfo ci){
        Gathering.render(poseStack, f);
        Gathering.clear();
    }
}
