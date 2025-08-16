package com.alex2k.dragonball.item;

import com.alex2k.dragonball.DragonBall;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DragonBall.MOD_ID);


    public static final DeferredItem<Item> SENSU_BEAN = ITEMS.register("sensu_bean",
            () -> new Item(new Item.Properties().food(ModFoodProperties.SENSU_BEAN)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
