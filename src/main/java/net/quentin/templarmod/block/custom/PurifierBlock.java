package net.quentin.templarmod.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class PurifierBlock extends Block {

    public PurifierBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        super.stepOn(world, pos, state, entity);

        // Check if the entity is a player
        if (entity instanceof Player) {
            Player player = (Player) entity;

            // Apply the glowing effect for 5 seconds (100 ticks)
            player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 2, 0, false, false));
        }
    }
}

