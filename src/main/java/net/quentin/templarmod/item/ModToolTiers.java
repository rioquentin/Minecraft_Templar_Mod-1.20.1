package net.quentin.templarmod.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.quentin.templarmod.TemplarMod;
import net.quentin.templarmod.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier SACRED_STEEL = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2500, 12f, 5f, 25,
                    ModTags.Blocks.NEEDS_SACRED_STEEL_TOOL, () -> Ingredient.of(ModItems.SACRED_STEEL_INGOT.get())),
            new ResourceLocation(TemplarMod.MOD_ID, "sacred_steel"), List.of(Tiers.NETHERITE), List.of());
}
