package net.quentin.templarmod.renderer;

import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;


public class ModPlayerRenderer extends PlayerRenderer {
    public ModPlayerRenderer(EntityRendererManager renderManager) {
        super(renderManager, true);
    }
}
