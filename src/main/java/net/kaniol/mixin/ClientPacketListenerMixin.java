package net.kaniol.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.kaniol.Gathering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundTakeItemEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;


@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin implements ClientGamePacketListener{
	@Shadow ClientLevel level;
	@Shadow final Minecraft minecraft = Minecraft.getInstance();

	//MC-120643 : Picking up an incomplete stack of items is not correctly detected
	//should be aware of it
	
	@Inject(at = @At("HEAD"), method = "handleTakeItemEntity")
	private void handleTakeItemEntity(ClientboundTakeItemEntityPacket clientboundTakeItemEntityPacket, CallbackInfo ci) {
		PacketUtils.ensureRunningOnSameThread(clientboundTakeItemEntityPacket, this, this.minecraft);
		Entity entity = this.level.getEntity(clientboundTakeItemEntityPacket.getItemId());
		if (entity != null && entity instanceof ItemEntity)
		{ 
			ItemEntity itemEntity = (ItemEntity) entity;
			Gathering.addItemStack(itemEntity.getItem());
		}
	}
}
