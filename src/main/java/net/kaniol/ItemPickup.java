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

    public float getAlpha(){
        if(this.countdown <= 0) return 0.01f;
        float f = 1 - (float)this.countdown / 200;
        f = 1 - f * f;
        if(f<=0.02f) return 0.02f;
        return f;
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
        this.countdown = 200;
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