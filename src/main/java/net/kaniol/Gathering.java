package net.kaniol;

import java.util.LinkedList;
import java.util.Queue;
import java.awt.Color;

import com.mojang.blaze3d.vertex.PoseStack;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Gathering implements ModInitializer{
    private static Queue<ItemPickup> pickups = new LinkedList<ItemPickup>();
    private static Minecraft minecraft = Minecraft.getInstance();

    @Override
    public void onInitialize() {
        
    }

    public static void clear() {
        // pickups.clear();
    }

    public static void render(PoseStack poseStack, float f){
        int yoffset = 0;
        for(ItemPickup itemPickup : pickups) {
            // System.out.println(itemStack.getDisplayName().getString()+" "+itemStack.getCount());
            renderGathering(poseStack, f, itemPickup.getItemStack(), yoffset);
            yoffset += 20;
            itemPickup.decreaseCountdown();
        }
        ItemPickup itemPickup2;
        while((itemPickup2 = pickups.peek()) != null && itemPickup2.getCountDown() <= 0){
            pickups.remove();
        }
    }

    public static void addItemStack(ItemStack itemStack) {
        // for(ItemPickup itemPickup : pickups) {
        //     ItemStack itemStack2 = itemPickup.getItemStack();
        //     if(itemStack.is(itemStack2.getItem()) && itemStack.getDamageValue() == itemStack2.getDamageValue() && ItemStack.tagMatches(itemStack, itemStack2)){
        //         itemPickup.grow(itemStack.getCount());
        //         return;
        //     }
        // }
        pickups.offer(new ItemPickup(itemStack.copy()));
        // if(pickups.size() > 5) pickups.poll();
        System.out.println(pickups.size());

        for(ItemPickup i : pickups) {
            System.out.println(i.getItemStack().getDisplayName().getString()+" "+i.getItemStack().getCount());
        }
    }
    
    private static void renderGathering(PoseStack poseStack, float f, ItemStack itemStack, int yoffset){
        if(itemStack.is(Items.AIR)) return;
        // System.out.println(itemStack.getDisplayName().getString());
        minecraft.getItemRenderer().renderGuiItem(itemStack, 20, 20 + yoffset);
        minecraft.font.draw(poseStack, new TextComponent(" * " + itemStack.getCount()), 35, 30 + yoffset, Color.WHITE.getRGB());
    }
}
