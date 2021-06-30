package net.kaniol;

import net.minecraft.world.item.ItemStack;

public class ItemPickup {
    private ItemStack itemStack;
    private int countdown = 100;
    
    public ItemPickup(ItemStack is){
        itemStack = is;
    }

    public ItemStack getItemStack(){
        return itemStack;
    }

    public int getCountDown(){
        return countdown;
    }

    public void decreaseCountdown(){
        countdown--;
    }

    public void resetCountdown(){
        countdown = 100;
    }

    public void grow(int i){
        itemStack.grow(i);
        this.resetCountdown();
    }

}