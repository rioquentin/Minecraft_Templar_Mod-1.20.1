package net.quentin.templarmod.item.custom;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
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

            // Client-side logic
            else {
                // Generate particles in a circle
                for (float angle = 0; angle < 360; angle += 2) {
                    double x = player.getX() + RADIUS * Math.cos(Math.toRadians(angle));
                    double z = player.getZ() + RADIUS * Math.sin(Math.toRadians(angle));
                    double y = player.getY() + 0.5D;

                    player.getCommandSenderWorld().addParticle(ParticleTypes.FLAME, x, y, z, 0, 0, 0);
                }

                // Render the magic circle at the player's feet
                renderMagicCircle(player);
            }
        }
    }

    private static void renderMagicCircle(Player player) {
        PoseStack matrixStack = new PoseStack();
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();

        ResourceLocation magicCircleTexture = new ResourceLocation("templarmod:textures/effect/holy_circle.png");

        RenderSystem.setShaderTexture(0, magicCircleTexture);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);


        matrixStack.pushPose();

        matrixStack.translate(player.getX(), player.getY(), player.getZ());
        matrixStack.scale(2.0F, 2.0F, 2.0F);
        matrixStack.translate(-0.5D, 0.05D, -0.5D);

        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.vertex(matrixStack.last().pose(), 0, 0, 1).uv(0, 1).endVertex();
        bufferBuilder.vertex(matrixStack.last().pose(), 1, 0, 1).uv(1, 1).endVertex();
        bufferBuilder.vertex(matrixStack.last().pose(), 1, 0, 0).uv(1, 0).endVertex();
        bufferBuilder.vertex(matrixStack.last().pose(), 0, 0, 0).uv(0, 0).endVertex();

        Tesselator.getInstance().end();

        matrixStack.popPose();


        RenderSystem.disableBlend();
    }


}
