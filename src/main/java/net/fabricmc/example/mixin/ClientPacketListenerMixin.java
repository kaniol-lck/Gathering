package net.fabricmc.example.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundTakeItemEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;


@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin{
	@Shadow ClientLevel level;

	//MC-120643 : Picking up an incomplete stack of items is not correctly detected
	//should be aware of it
	
	@Inject(at = @At("HEAD"), method = "handleTakeItemEntity")
	private void handleTakeItemEntity(ClientboundTakeItemEntityPacket clientboundTakeItemEntityPacket, CallbackInfo ci) {
		Entity entity = this.level.getEntity(clientboundTakeItemEntityPacket.getItemId());
		if (entity != null && entity instanceof ItemEntity)
		{ 
			ItemEntity itemEntity = (ItemEntity)entity;
			ItemStack itemStack = itemEntity.getItem();
			System.out.println("item "+itemStack.getDisplayName().getString()+" * " + itemStack.getCount() + " picked up.");
		}
	}
}
