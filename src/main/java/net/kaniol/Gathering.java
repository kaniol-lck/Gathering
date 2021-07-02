package net.kaniol;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import com.mojang.blaze3d.vertex.PoseStack;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Gathering implements ModInitializer{
    private static List<ItemPickup> pickups = new ArrayList<ItemPickup>();
    private static Minecraft minecraft = Minecraft.getInstance();

    @Override
    public void onInitialize() {
        
    }

    public static void render(PoseStack poseStack, float f){
        for(int i = 0; i < pickups.size(); i++) {
            ItemPickup itemPickup = pickups.get(i);
            int yoffset = itemPickup.getOffset();
            renderGathering(poseStack, f, itemPickup.getItemStack(), yoffset);
            itemPickup.decreaseCountdown();
        }
        while(!pickups.isEmpty() && pickups.get(pickups.size() - 1).getCountDown() <= 0){
            pickups.remove(pickups.size() - 1);
        }
    }

    public static void addItemStack(ItemStack itemStack) {
        //reset fade in time
        for (ItemPickup itemPickup : pickups) {
            itemPickup.resetFadeInOffset();
        }

        int i;
        for(i = 0; i < pickups.size(); i++) {
            ItemStack itemStack2 = pickups.get(i).getItemStack();
            if(itemStack.is(itemStack2.getItem()) && itemStack.getDamageValue() == itemStack2.getDamageValue() && ItemStack.tagMatches(itemStack, itemStack2)){
                pickups.get(i).grow(itemStack.getCount());
                pickups.add(0, pickups.remove(i));
                for(int j = 0; j < i; j++){
                    pickups.get(j + 1).moveDown();
                }
                return;
            }
        }

        for(int j = 0; j < pickups.size(); j++){
            pickups.get(j).moveDown();
        }
        pickups.add(0, new ItemPickup(itemStack.copy()));
        
    }
    
    private static void renderGathering(PoseStack poseStack, float f, ItemStack itemStack, int yoffset){
        if(itemStack.is(Items.AIR)) return;
        minecraft.getItemRenderer().renderGuiItem(itemStack, 20, 20 + yoffset);
        minecraft.font.draw(poseStack, new TextComponent(" * " + itemStack.getCount()), 35, 30 + yoffset, Color.WHITE.getRGB());
    }
}
