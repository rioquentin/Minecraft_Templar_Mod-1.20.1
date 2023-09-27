package net.quentin.templarmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.quentin.templarmod.TemplarMod;
import net.quentin.templarmod.block.ModBlocks;

public class ModCreativeModTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TemplarMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TEMPLAR_TAB = CREATIVE_MODE_TABS.register("templar_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TEMPLAR_ICON.get()))
                    .title(Component.translatable("creativetab.templar_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.RAW_SACRED_STEEL.get());
                        pOutput.accept(ModItems.SACRED_STEEL_INGOT.get());
                        pOutput.accept(ModBlocks.RAW_SACRED_STEEL_BLOCK.get());
                        pOutput.accept(ModBlocks.SACRED_STEEL_BLOCK.get());
                        pOutput.accept(ModBlocks.SACRED_STEEL_ORE.get());
                        pOutput.accept(ModItems.CALICE.get());
                        pOutput.accept(ModBlocks.PURIFIER.get());
                        pOutput.accept(ModItems.HOLY_CROSS.get());
                        pOutput.accept(ModItems.TEMPLAR_SWORD.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
