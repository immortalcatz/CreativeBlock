package me.dags.creativeblock.dynmap.models;

import me.dags.creativeblock.definition.BlockTextures;
import org.dynmap.modsupport.*;

/**
 * @author dags <dags@dags.me>
 */
public class BlockDoublePlant extends AbstractModel {

    @Override
    void applyModel(ModTextureDefinition definition, String name)
    {
        definition.getModelDefinition().addPlantModel(name).setMetaValue(0);
        definition.getModelDefinition().addPlantModel(name).setMetaValue(1);
    }

    @Override
    void applyProperties(BlockTextureRecord record)
    {}

    @Override
    public BlockTextureRecord textureRecord(ModTextureDefinition definition, String name, BlockTextures textures)
    {
        TextureFile lower = definition.registerTextureFile(textures.get("#bottom"));
        BlockTextureRecord recordLower = definition.addBlockTextureRecord(name);
        recordLower.setMetaValue(0);
        recordLower.setSideTexture(lower, BlockSide.ALLSIDES);

        TextureFile upper = definition.registerTextureFile(textures.get("#top"));
        BlockTextureRecord recordUpper = definition.addBlockTextureRecord(name);
        recordUpper.setMetaValue(1);
        recordUpper.setSideTexture(upper, BlockSide.ALLSIDES);
        recordUpper.setSideTexture(upper, BlockSide.TOP);
        return recordUpper;
    }
}
