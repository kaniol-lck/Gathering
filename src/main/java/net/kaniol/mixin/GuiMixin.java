package net.kaniol.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.Gui;

import net.kaniol.Gathering;

@Mixin(Gui.class)
public class GuiMixin {
    
    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo ci){
        Gathering.print();
        Gathering.clear();
    }
}
