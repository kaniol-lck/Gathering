package net.kaniol;

import java.util.HashMap;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.BaseComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

public class Gathering implements ModInitializer{
    private static HashMap<Component, Integer> itemCounter = new HashMap<Component, Integer>();
    private static Minecraft minecraft = Minecraft.getInstance();

    @Override
    public void onInitialize() {
        
    }

    public static void clear() {
        itemCounter.clear();
    }

    public static void addItem(Component iComponent, int count){
        if(itemCounter.containsKey(iComponent))
            itemCounter.put(iComponent, itemCounter.get(iComponent) + count);
        else
            itemCounter.put(iComponent, count);
    }

    public static void print(){
        for(HashMap.Entry<Component, Integer> entry : itemCounter.entrySet()){
            System.out.println(entry.getKey().getString()+" * "+entry.getValue()+" was picked.");
            minecraft.gui.getChat().addMessage(new TextComponent("").append(entry.getKey()).append(new TextComponent(" * " + entry.getValue())));
        }
    }
}
