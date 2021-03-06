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
import org.dynmap.modsupport.*;

/**
 * @author dags <dags@dags.me>
 */

public class BlockPlant extends AbstractModel
{
    @Override
    void applyModel(ModTextureDefinition definition, String name)
    {
        definition.getModelDefinition().addPlantModel(name);
    }

    @Override
    void applyProperties(BlockTextureRecord record)
    {}

    public BlockTextureRecord textureRecord(ModTextureDefinition definition, String name, BlockTextures textures)
    {
        TextureFile side = definition.registerTextureFile(textures.get("#side"));
        BlockTextureRecord record = definition.addBlockTextureRecord(name);
        record.setSideTexture(side, BlockSide.ALLSIDES);
        record.setSideTexture(side, BlockSide.TOP);
        return record;
    }
}
