package net.quentin.templarmod.renderer;

import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class ModPlayerRenderer extends PlayerRenderer {

    private static final ResourceLocation MAGIC_CIRCLE_TEXTURE = new ResourceLocation("templarmod:textures/effect/holy_circle.png");

    public ModPlayerRenderer(EntityRendererProvider.Context context, boolean useSlimModel) {
        super(context, useSlimModel);
    }

    @Override
    public void render(AbstractClientPlayer player, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(player, entityYaw, partialTicks, poseStack, buffer, packedLight);

        // Here, we'll add the logic to render the magic circle at the player's feet
        renderMagicCircle(player, poseStack, buffer, packedLight);
    }

    private void renderMagicCircle(AbstractClientPlayer player, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        // Adjust these values based on the size and positioning of your texture/model
        float scale = 1.0F;
        Vec3 offset = new Vec3(0, 0.01, 0); // Small offset to lift slightly above the ground

        poseStack.translate(offset.x(), offset.y(), offset.z());
        poseStack.scale(scale, scale, scale);

        // Render your magic circle texture here.
        // This is a simplified example. You'll likely need to use a custom model or quad rendering to display your texture.

        poseStack.popPose();
    }
}
