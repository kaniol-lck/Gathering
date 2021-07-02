package net.kaniol;

import net.minecraft.world.item.ItemStack;

public class ItemPickup {
    private ItemStack itemStack;
    private int countdown = 200;
    private int index = 0;
    private int fadeInOffset = 0;
    
    public ItemPickup(ItemStack is){
        this.itemStack = is;
    }

    public ItemStack getItemStack(){
        return this.itemStack;
    }

    public int getCountDown(){
        return this.countdown;
    }

    public int getOffset(){
        if (this.index == 0)
            return 0;
        else
            return this.index * 20 - fadeInOffset;
    }

    public void decreaseCountdown(){
        if(this.countdown > 0) this.countdown--;
        if(this.fadeInOffset > 0) this.fadeInOffset--;
    }

    public void resetCountdown(){
        this.countdown = 100;
    }

    public void resetOffset(){
        this.index = 0;
        this.fadeInOffset = 0;
    }

    public void resetFadeInOffset(){
        this.fadeInOffset = 0;
    }

    public void grow(int i){
        this.itemStack.grow(i);
        this.resetCountdown();
        this.resetOffset();
    }

    public void moveDown(){
        this.index += 1;
        this.fadeInOffset =20;
    }

}