/*
 * The MIT License (MIT)
 *
 * Copyright (c) dags <https://www.dags.me>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.dags.creativeblock.dynmap.models;

import me.dags.creativeblock.definition.BlockTextures;
import net.minecraft.block.Block;
import org.dynmap.modsupport.BlockSide;
import org.dynmap.modsupport.BlockTextureRecord;
import org.dynmap.modsupport.ModTextureDefinition;
import org.dynmap.modsupport.TextureFile;

/**
 * @author dags <dags@dags.me>
 */

public abstract class AbstractModel implements ModelMaker
{
    @Override
    public void registerBlock(ModTextureDefinition definition, Block block, BlockTextures textures)
    {
        String name = block.getUnlocalizedName().substring("tile.".length());
        applyModel(definition, name);
        BlockTextureRecord record = textureRecord(definition, name, textures);
        applyProperties(record);
    }

    public BlockTextureRecord textureRecord(ModTextureDefinition definition, String name, BlockTextures textures)
    {
        TextureFile top = definition.registerTextureFile(textures.get("#top"));
        TextureFile side = definition.registerTextureFile(textures.get("#side"));
        TextureFile bottom = definition.registerTextureFile(textures.get("#bottom"));

        BlockTextureRecord record = definition.addBlockTextureRecord(name);
        record.setSideTexture(top, BlockSide.TOP);
        record.setSideTexture(bottom, BlockSide.BOTTOM);
        record.setSideTexture(side, BlockSide.NORTH);
        record.setSideTexture(side, BlockSide.EAST);
        record.setSideTexture(side, BlockSide.SOUTH);
        record.setSideTexture(side, BlockSide.WEST);
        return record;
    }

    abstract void applyModel(ModTextureDefinition definition, String name);

    abstract void applyProperties(BlockTextureRecord record);
}
