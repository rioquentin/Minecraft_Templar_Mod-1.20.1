package net.quentin.templarmod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.quentin.templarmod.TemplarMod;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> HOLY_BLOCKS = tag("holy_blocks");
        public static final TagKey<Block> NEEDS_SACRED_STEEL_TOOL = tag("needs_sacred_steel_tool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(TemplarMod.MOD_ID, name));
        }
    }

    public static class Items {


        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(TemplarMod.MOD_ID, name));
        }
    }
}
