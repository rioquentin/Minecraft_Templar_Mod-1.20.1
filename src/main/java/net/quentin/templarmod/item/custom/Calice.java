package net.quentin.templarmod.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.UseAnim;

public class Calice extends Item {

    private static final int REGENERATION_DURATION = 10 * 20; // 10 seconds in ticks
    private static final int DRINK_DURATION = 32; // Standard drink duration in ticks
    private static final int COOLDOWN_DURATION = 60 * 20; // 60 seconds in ticks

    public Calice(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        if (pPlayer.getCooldowns().isOnCooldown(this)) return InteractionResultHolder.pass(pPlayer.getItemInHand(pHand));

        pPlayer.startUsingItem(pHand);
        return InteractionResultHolder.success(pPlayer.getItemInHand(pHand));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        Player player = pEntityLiving instanceof Player ? (Player)pEntityLiving : null;
        if (player != null && !pLevel.isClientSide) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, REGENERATION_DURATION, 2));
            player.getCooldowns().addCooldown(this, COOLDOWN_DURATION);
            player.playSound(SoundEvents.GENERIC_DRINK, 1.0F, 1.0F);
        }
        return pStack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return DRINK_DURATION;
    }
}



