package com.alex2k.dragonball.item;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties SENSU_BEAN = new FoodProperties.Builder().nutrition(20).saturationModifier(2).alwaysEdible().fast()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60), 1).build();

}