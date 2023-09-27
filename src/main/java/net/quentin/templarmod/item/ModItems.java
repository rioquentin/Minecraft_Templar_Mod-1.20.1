package net.quentin.templarmod.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.quentin.templarmod.TemplarMod;
import net.quentin.templarmod.item.custom.Calice;
import net.quentin.templarmod.item.custom.HolyCross;
import net.quentin.templarmod.item.custom.TemplarSword;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TemplarMod.MOD_ID);

    public static final RegistryObject<Item> TEMPLAR_ICON =
            ITEMS.register("templar_icon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_SACRED_STEEL =
            ITEMS.register("raw_sacred_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SACRED_STEEL_INGOT =
            ITEMS.register("sacred_steel_ingot", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CALICE =
            ITEMS.register("calice", () -> new Calice(new Item.Properties()));

    public static final RegistryObject<Item> HOLY_CROSS =
            ITEMS.register("holy_cross", () -> new HolyCross(new Item.Properties()));
    public static final RegistryObject<Item> TEMPLAR_SWORD = ITEMS.register("templar_sword",
            () -> new TemplarSword(ModToolTiers.SACRED_STEEL, 4, 1, new Item.Properties()));
    public static final RegistryObject<Item> TEMPLAR_PICKAXE =
            ITEMS.register("templar_pickaxe", () -> new PickaxeItem(ModToolTiers.SACRED_STEEL, 2, 2, new Item.Properties()));
    public static final RegistryObject<Item> TEMPLAR_AXE =
            ITEMS.register("templar_axe", () -> new AxeItem(ModToolTiers.SACRED_STEEL, 3, 1.5f, new Item.Properties()));
    public static final RegistryObject<Item> TEMPLAR_SHOVEL =
            ITEMS.register("templar_shovel", () -> new ShovelItem(ModToolTiers.SACRED_STEEL, 1, 1, new Item.Properties()));
    public static final RegistryObject<Item> TEMPLAR_HOE =
            ITEMS.register("templar_hoe", () -> new HoeItem(ModToolTiers.SACRED_STEEL, 1, 1, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
