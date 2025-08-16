package com.alex2k.dragonball.item;

import com.alex2k.dragonball.DragonBall;
import com.alex2k.dragonball.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DragonBall.MOD_ID);

    public static final Supplier<CreativeModeTab> DRAGON_BALL_TAB = CREATIVE_MODE_TAB.register("dragon_ball_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.DRAGON_BALL.get()))
                    .title(Component.translatable("creativetab.dragonball.dragon_ball_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SENSU_BEAN);
                        output.accept(ModBlocks.DRAGON_BALL);
                    }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}