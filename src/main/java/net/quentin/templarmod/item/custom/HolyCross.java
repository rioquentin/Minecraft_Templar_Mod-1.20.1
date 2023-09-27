package net.quentin.templarmod.item.custom;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.Level;

@Mod.EventBusSubscriber(modid = "templarmod")
public class HolyCross extends Item {
    private static final double RADIUS = 5.0D;

    public HolyCross(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BLOCK;  // Mimic the shield's blocking animation
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;  // Standard long duration, similar to the shield
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        if (!pLevel.isClientSide) {
            AABB area = new AABB(
                    pPlayer.getX() - RADIUS, pPlayer.getY() - RADIUS, pPlayer.getZ() - RADIUS,
                    pPlayer.getX() + RADIUS, pPlayer.getY() + RADIUS, pPlayer.getZ() + RADIUS
            );

            for (LivingEntity entity : pLevel.getEntitiesOfClass(LivingEntity.class, area)) {
                if (entity != pPlayer) {
                    entity.setSecondsOnFire(5);
                }
            }
        }
        pPlayer.startUsingItem(pHand);  // Player starts the "using" action, similar to the shield
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pHand));
    }

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity entity, int timeLeft) {
        if (!world.isClientSide && entity instanceof Player) {
            AABB area = new AABB(
                    entity.getX() - RADIUS, entity.getY() - RADIUS, entity.getZ() - RADIUS,
                    entity.getX() + RADIUS, entity.getY() + RADIUS, entity.getZ() + RADIUS
            );

            for (LivingEntity targetEntity : world.getEntitiesOfClass(LivingEntity.class, area)) {
                if (targetEntity.isOnFire()) {
                    targetEntity.clearFire();  // Clear the fire from the entity
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        // Check if player is using the HolyCross
        if (player.isUsingItem() && player.getUseItem().getItem() instanceof HolyCross) {

            // Server-side logic: Set entities on fire
            if (!player.getCommandSenderWorld().isClientSide) {
                AABB area = new AABB(
                        player.getX() - RADIUS, player.getY() - RADIUS, player.getZ() - RADIUS,
                        player.getX() + RADIUS, player.getY() + RADIUS, player.getZ() + RADIUS
                );

                for (LivingEntity entity : player.getCommandSenderWorld().getEntitiesOfClass(LivingEntity.class, area)) {
                    if (entity != player) {
                        entity.setSecondsOnFire(2);
                    }
                }
            }

            // Client-side logic: Generate particles in a circle
            else {
                for (float angle = 0; angle < 360; angle += 2) {
                    double x = player.getX() + RADIUS * Math.cos(Math.toRadians(angle));
                    double z = player.getZ() + RADIUS * Math.sin(Math.toRadians(angle));
                    double y = player.getY() + 0.5D;

                    player.getCommandSenderWorld().addParticle(ParticleTypes.FLAME, x, y, z, 0, 0, 0);
                }
            }
        }
    }

}
